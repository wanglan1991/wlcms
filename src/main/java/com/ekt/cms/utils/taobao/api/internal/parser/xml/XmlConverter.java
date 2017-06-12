package com.ekt.cms.utils.taobao.api.internal.parser.xml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.ekt.cms.utils.taobao.api.ApiException;
import com.ekt.cms.utils.taobao.api.internal.mapping.Converter;
import com.ekt.cms.utils.taobao.api.internal.mapping.Converters;
import com.ekt.cms.utils.taobao.api.internal.mapping.Reader;
import com.ekt.cms.utils.taobao.api.internal.util.StringUtils;
import com.ekt.cms.utils.taobao.api.internal.util.XmlUtils;

/**
 * JSON格式转换器。
 * 
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public class XmlConverter implements Converter {

	public <T> T toResponse(String rsp, Class<T> clazz) throws ApiException {
		Element root = XmlUtils.getRootElementFromString(rsp);
		return getModelFromXML(root, clazz);
	}

	private <T> T getModelFromXML(final Element element, Class<T> clazz) throws ApiException {
		if (element == null)
			return null;

		return Converters.convert(clazz, new Reader() {
			public boolean hasReturnField(Object name) {
				Element childE = XmlUtils.getChildElement(element, (String) name);
				return childE != null;
			}

			public Object getPrimitiveObject(Object name) {
				return XmlUtils.getChildElementValue(element, (String) name);
			}

			public Object getObject(Object name, Class<?> type) throws ApiException {
				Element childE = XmlUtils.getChildElement(element, (String) name);
				if (childE != null) {
					return getModelFromXML(childE, type);
				} else {
					return null;
				}
			}

			public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) throws ApiException {
				List<Object> list = null;
				Element listE = XmlUtils.getChildElement(element, (String) listName);
				if (listE != null) {
					list = new ArrayList<Object>();
					List<Element> itemEs = XmlUtils.getChildElements(listE, (String) itemName);
					for (Element itemE : itemEs) {
						Object obj = null;
						String value = XmlUtils.getElementValue(itemE);

						if (String.class.isAssignableFrom(subType)) {
							obj = value;
						} else if (Long.class.isAssignableFrom(subType)) {
							obj = Long.valueOf(value);
						} else if (Integer.class.isAssignableFrom(subType)) {
							obj = Integer.valueOf(value);
						} else if (Boolean.class.isAssignableFrom(subType)) {
							obj = Boolean.valueOf(value);
						} else if (Date.class.isAssignableFrom(subType)) {
							obj = StringUtils.parseDateTime(value);
						} else {
							obj = getModelFromXML(itemE, subType);
						}
						if (obj != null) list.add(obj);
					}
				}
				return list;
			}
		});
	}

}
