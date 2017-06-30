/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.demo.resources.exporter.test.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.demo.resources.exporter.test.service.ResourceExporterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the resource exporter local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.demo.resources.exporter.test.service.impl.ResourceExporterLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.demo.resources.exporter.test.service.impl.ResourceExporterLocalServiceImpl
 * @see com.liferay.demo.resources.exporter.test.service.ResourceExporterLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ResourceExporterLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ResourceExporterLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.demo.resources.exporter.test.service.ResourceExporterLocalServiceUtil} to access the resource exporter local service.
	 */

	/**
	 * Returns the column exporter local service.
	 *
	 * @return the column exporter local service
	 */
	public com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalService getColumnExporterLocalService() {
		return columnExporterLocalService;
	}

	/**
	 * Sets the column exporter local service.
	 *
	 * @param columnExporterLocalService the column exporter local service
	 */
	public void setColumnExporterLocalService(
		com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalService columnExporterLocalService) {
		this.columnExporterLocalService = columnExporterLocalService;
	}

	/**
	 * Returns the layout exporter local service.
	 *
	 * @return the layout exporter local service
	 */
	public com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService getLayoutExporterLocalService() {
		return layoutExporterLocalService;
	}

	/**
	 * Sets the layout exporter local service.
	 *
	 * @param layoutExporterLocalService the layout exporter local service
	 */
	public void setLayoutExporterLocalService(
		com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService layoutExporterLocalService) {
		this.layoutExporterLocalService = layoutExporterLocalService;
	}

	/**
	 * Returns the portlet preferences exporter local service.
	 *
	 * @return the portlet preferences exporter local service
	 */
	public com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalService getPortletPreferencesExporterLocalService() {
		return portletPreferencesExporterLocalService;
	}

	/**
	 * Sets the portlet preferences exporter local service.
	 *
	 * @param portletPreferencesExporterLocalService the portlet preferences exporter local service
	 */
	public void setPortletPreferencesExporterLocalService(
		com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalService portletPreferencesExporterLocalService) {
		this.portletPreferencesExporterLocalService = portletPreferencesExporterLocalService;
	}

	/**
	 * Returns the resource exporter local service.
	 *
	 * @return the resource exporter local service
	 */
	public ResourceExporterLocalService getResourceExporterLocalService() {
		return resourceExporterLocalService;
	}

	/**
	 * Sets the resource exporter local service.
	 *
	 * @param resourceExporterLocalService the resource exporter local service
	 */
	public void setResourceExporterLocalService(
		ResourceExporterLocalService resourceExporterLocalService) {
		this.resourceExporterLocalService = resourceExporterLocalService;
	}

	/**
	 * Returns the site exporter local service.
	 *
	 * @return the site exporter local service
	 */
	public com.liferay.demo.resources.exporter.test.service.SiteExporterLocalService getSiteExporterLocalService() {
		return siteExporterLocalService;
	}

	/**
	 * Sets the site exporter local service.
	 *
	 * @param siteExporterLocalService the site exporter local service
	 */
	public void setSiteExporterLocalService(
		com.liferay.demo.resources.exporter.test.service.SiteExporterLocalService siteExporterLocalService) {
		this.siteExporterLocalService = siteExporterLocalService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ResourceExporterLocalService.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalService.class)
	protected com.liferay.demo.resources.exporter.test.service.ColumnExporterLocalService columnExporterLocalService;
	@BeanReference(type = com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService.class)
	protected com.liferay.demo.resources.exporter.test.service.LayoutExporterLocalService layoutExporterLocalService;
	@BeanReference(type = com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalService.class)
	protected com.liferay.demo.resources.exporter.test.service.PortletPreferencesExporterLocalService portletPreferencesExporterLocalService;
	@BeanReference(type = ResourceExporterLocalService.class)
	protected ResourceExporterLocalService resourceExporterLocalService;
	@BeanReference(type = com.liferay.demo.resources.exporter.test.service.SiteExporterLocalService.class)
	protected com.liferay.demo.resources.exporter.test.service.SiteExporterLocalService siteExporterLocalService;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}