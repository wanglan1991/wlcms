package com.ekt.cms.utils.taobao.api.internal.parser.xml;

import com.ekt.cms.utils.taobao.api.ApiException;
import com.ekt.cms.utils.taobao.api.TaobaoParser;
import com.ekt.cms.utils.taobao.api.internal.mapping.Converter;
import com.ekt.cms.utils.taobao.api.qimen.api.QimenResponse;

public class QimenXmlParser<T extends QimenResponse> implements TaobaoParser<T> {

	private Class<T> clazz;

	public QimenXmlParser(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T parse(String rsp) throws ApiException {
		Converter converter = new XmlConverter();
		return converter.toResponse(rsp, clazz);
	}

	public Class<T> getResponseClass() {
		return clazz;
	}

}
