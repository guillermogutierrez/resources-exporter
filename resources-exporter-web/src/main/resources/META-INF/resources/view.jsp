<%@ include file="init.jsp" %>

<p>
	<b><liferay-ui:message key="export_site_ExportSitemvcportlet.caption"/></b>
</p>

		
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/exportzipfile" var="sidebarPanelURL">
	</liferay-portlet:resourceURL>
		
<a href="<%= sidebarPanelURL %>"> Export file </a>