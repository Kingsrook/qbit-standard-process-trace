/*
 * QQQ - Low-code Application Framework for Engineers.
 * Copyright (C) 2021-2025.  Kingsrook, LLC
 * 651 N Broad St Ste 205 # 6917 | Middletown DE 19709 | United States
 * contact@kingsrook.com
 * https://github.com/Kingsrook/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.kingsrook.qbits.standardprocesstrace.utils;


import com.kingsrook.qbits.standardprocesstrace.model.ProcessTrace;
import com.kingsrook.qqq.backend.core.actions.dashboard.widgets.RecordListWidgetRenderer;
import com.kingsrook.qqq.backend.core.model.actions.tables.query.QCriteriaOperator;
import com.kingsrook.qqq.backend.core.model.actions.tables.query.QFilterOrderBy;
import com.kingsrook.qqq.backend.core.model.actions.tables.query.QQueryFilter;
import com.kingsrook.qqq.backend.core.model.metadata.dashboard.QWidgetMetaData;
import com.kingsrook.qqq.backend.core.utils.StringUtils;


/*******************************************************************************
 ** factory/builder class, to create instances of RecordListWidgetRenderer for
 ** specific tables and/or processes within a table.
 *******************************************************************************/
public class RecentProcessTracesWidgetBuilder
{

   /***************************************************************************
    **
    ***************************************************************************/
   public static QWidgetMetaData buildWidget(String tableName)
   {
      QWidgetMetaData widgetMetaData = RecordListWidgetRenderer.widgetMetaDataBuilder(tableName + "RecentProcessTraces")
         .withTableName(ProcessTrace.TABLE_NAME)
         .withMaxRows(20)
         .withLabel("Recent Process Traces")
         .withFilter(new QQueryFilter()
            .withCriteria("qqqTable.name", QCriteriaOperator.EQUALS, tableName)
            .withCriteria("keyRecordId", QCriteriaOperator.EQUALS, "${input.id}")
            .withOrderBy(new QFilterOrderBy("id", false))
         ).getWidgetMetaData();

      return (widgetMetaData);
   }



   /***************************************************************************
    **
    ***************************************************************************/
   public static QWidgetMetaData buildWidget(String tableName, String processName)
   {
      QWidgetMetaData widgetMetaData = RecordListWidgetRenderer.widgetMetaDataBuilder(tableName + StringUtils.ucFirst(processName) + "RecentProcessTraces")
         .withTableName(ProcessTrace.TABLE_NAME)
         .withMaxRows(20)
         .withLabel("Recent Process Traces")
         .withFilter(new QQueryFilter()
            .withCriteria("qqqProcess.name", QCriteriaOperator.EQUALS, processName)
            .withCriteria("qqqTable.name", QCriteriaOperator.EQUALS, tableName)
            .withCriteria("keyRecordId", QCriteriaOperator.EQUALS, "${input.id}")
            .withOrderBy(new QFilterOrderBy("id", false))
         ).getWidgetMetaData();

      return (widgetMetaData);
   }

}
