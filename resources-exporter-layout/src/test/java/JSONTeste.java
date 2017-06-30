import org.junit.Test;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class JSONTeste {

	@Test
	public void testColumnExportOnePortlet() {

		JSONObject portletID = JSONFactoryUtil.createJSONObject();

		portletID.put("node1", "node 1 cvalue1");

		System.out.println(portletID.toJSONString());
	}

}
