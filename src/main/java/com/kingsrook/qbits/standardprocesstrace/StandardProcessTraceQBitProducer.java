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

package com.kingsrook.qbits.standardprocesstrace;


import com.kingsrook.qbits.standardprocesstrace.model.ProcessTrace;
import com.kingsrook.qbits.standardprocesstrace.model.ProcessTraceSummaryLine;
import com.kingsrook.qbits.standardprocesstrace.model.ProcessTraceSummaryLineRecordInt;
import com.kingsrook.qqq.backend.core.exceptions.QException;
import com.kingsrook.qqq.backend.core.model.metadata.MetaDataProducerHelper;
import com.kingsrook.qqq.backend.core.model.metadata.MetaDataProducerInterface;
import com.kingsrook.qqq.backend.core.model.metadata.MetaDataProducerMultiOutput;
import com.kingsrook.qqq.backend.core.model.metadata.QInstance;
import com.kingsrook.qqq.backend.core.model.metadata.layout.QAppSection;
import com.kingsrook.qqq.backend.core.model.metadata.producers.MetaDataCustomizerInterface;
import com.kingsrook.qqq.backend.core.model.metadata.tables.QTableMetaData;
import com.kingsrook.qqq.backend.core.utils.StringUtils;


/*******************************************************************************
 **
 *******************************************************************************/
public class StandardProcessTraceQBitProducer implements MetaDataProducerInterface<MetaDataProducerMultiOutput>
{
   private StandardProcessTraceQBitConfig              standardProcessTraceQBitConfig;
   private MetaDataCustomizerInterface<QTableMetaData> tableMetaDataCustomizer;



   /***************************************************************************
    *
    ***************************************************************************/
   public static QAppSection getAppSection(QInstance qInstance)
   {
      return (new QAppSection().withName("processTrace")
         .withTable(ProcessTrace.TABLE_NAME)
         .withTable(ProcessTraceSummaryLine.TABLE_NAME)
         .withTable(ProcessTraceSummaryLineRecordInt.TABLE_NAME));
   }



   /***************************************************************************
    **
    ***************************************************************************/
   @Override
   public MetaDataProducerMultiOutput produce(QInstance qInstance) throws QException
   {
      MetaDataProducerHelper.processAllMetaDataProducersInPackage(qInstance, ProcessTrace.class.getPackageName(), tableMetaDataCustomizer);
      MetaDataProducerHelper.processAllMetaDataProducersInPackage(qInstance, getClass().getPackageName() + ".metadata", tableMetaDataCustomizer);

      //////////////////////////////////////////////////////////////////////////////////////
      // set the possible value source on the processTrace.userId field, if so configured //
      //////////////////////////////////////////////////////////////////////////////////////
      if(standardProcessTraceQBitConfig != null && StringUtils.hasContent(standardProcessTraceQBitConfig.getUserIdPossibleValueSourceName()))
      {
         qInstance.getTable(ProcessTrace.TABLE_NAME).getField("userId").setPossibleValueSourceName(standardProcessTraceQBitConfig.getUserIdPossibleValueSourceName());
      }

      ////////////////////////////////////////////////////////////////////////////////////////////////////
      // we actually called MetaDataProducerHelper ourselves, so, don't have anything to return here... //
      ////////////////////////////////////////////////////////////////////////////////////////////////////
      return new MetaDataProducerMultiOutput();
   }



   /*******************************************************************************
    ** Getter for standardProcessTraceQBitConfig
    *******************************************************************************/
   public StandardProcessTraceQBitConfig getStandardProcessTraceQBitConfig()
   {
      return (this.standardProcessTraceQBitConfig);
   }



   /*******************************************************************************
    ** Setter for standardProcessTraceQBitConfig
    *******************************************************************************/
   public void setStandardProcessTraceQBitConfig(StandardProcessTraceQBitConfig standardProcessTraceQBitConfig)
   {
      this.standardProcessTraceQBitConfig = standardProcessTraceQBitConfig;
   }



   /*******************************************************************************
    ** Fluent setter for standardProcessTraceQBitConfig
    *******************************************************************************/
   public StandardProcessTraceQBitProducer withStandardProcessTraceQBitConfig(StandardProcessTraceQBitConfig standardProcessTraceQBitConfig)
   {
      this.standardProcessTraceQBitConfig = standardProcessTraceQBitConfig;
      return (this);
   }



   /*******************************************************************************
    ** Getter for tableMetaDataCustomizer
    *******************************************************************************/
   public MetaDataCustomizerInterface<QTableMetaData> getTableMetaDataCustomizer()
   {
      return (this.tableMetaDataCustomizer);
   }



   /*******************************************************************************
    ** Setter for tableMetaDataCustomizer
    *******************************************************************************/
   public void setTableMetaDataCustomizer(MetaDataCustomizerInterface<QTableMetaData> tableMetaDataCustomizer)
   {
      this.tableMetaDataCustomizer = tableMetaDataCustomizer;
   }



   /*******************************************************************************
    ** Fluent setter for tableMetaDataCustomizer
    *******************************************************************************/
   public StandardProcessTraceQBitProducer withTableMetaDataCustomizer(MetaDataCustomizerInterface<QTableMetaData> tableMetaDataCustomizer)
   {
      this.tableMetaDataCustomizer = tableMetaDataCustomizer;
      return (this);
   }

}
