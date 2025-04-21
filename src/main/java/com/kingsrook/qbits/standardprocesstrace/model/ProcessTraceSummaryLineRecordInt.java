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


package com.kingsrook.qbits.standardprocesstrace.model;


import java.util.List;
import com.kingsrook.qbits.standardprocesstrace.utils.ProcessTraceSummaryLineRecordIntTableCustomizer;
import com.kingsrook.qqq.backend.core.actions.customizers.TableCustomizers;
import com.kingsrook.qqq.backend.core.exceptions.QException;
import com.kingsrook.qqq.backend.core.model.data.QField;
import com.kingsrook.qqq.backend.core.model.data.QRecord;
import com.kingsrook.qqq.backend.core.model.data.QRecordEntity;
import com.kingsrook.qqq.backend.core.model.metadata.QInstance;
import com.kingsrook.qqq.backend.core.model.metadata.code.QCodeReference;
import com.kingsrook.qqq.backend.core.model.metadata.fields.AdornmentType;
import com.kingsrook.qqq.backend.core.model.metadata.fields.FieldAdornment;
import com.kingsrook.qqq.backend.core.model.metadata.joins.QJoinMetaData;
import com.kingsrook.qqq.backend.core.model.metadata.layout.QIcon;
import com.kingsrook.qqq.backend.core.model.metadata.producers.MetaDataCustomizerInterface;
import com.kingsrook.qqq.backend.core.model.metadata.producers.annotations.QMetaDataProducingEntity;
import com.kingsrook.qqq.backend.core.model.metadata.tables.ExposedJoin;
import com.kingsrook.qqq.backend.core.model.metadata.tables.QFieldSection;
import com.kingsrook.qqq.backend.core.model.metadata.tables.QTableMetaData;
import com.kingsrook.qqq.backend.core.model.metadata.tables.Tier;
import com.kingsrook.qqq.backend.core.model.tables.QQQTable;


/*******************************************************************************
 ** QRecord Entity for com.kingsrook.qbits.standardprocesstrace.model.ProcessTraceSummaryLineRecordInt table
 *******************************************************************************/
@QMetaDataProducingEntity(
   produceTableMetaData = true,
   tableMetaDataCustomizer = ProcessTraceSummaryLineRecordInt.TableMetaDataCustomizer.class
)
public class ProcessTraceSummaryLineRecordInt extends QRecordEntity
{
   public static final String TABLE_NAME = "processTraceSummaryLineRecordInt";



   /***************************************************************************
    **
    ***************************************************************************/
   public static class TableMetaDataCustomizer implements MetaDataCustomizerInterface<QTableMetaData>
   {

      /***************************************************************************
       **
       ***************************************************************************/
      @Override
      public QTableMetaData customizeMetaData(QInstance qInstance, QTableMetaData table) throws QException
      {
         String parentJoinName      = QJoinMetaData.makeInferredJoinName(ProcessTraceSummaryLine.TABLE_NAME, ProcessTraceSummaryLineRecordInt.TABLE_NAME);
         String grandparentJoinName = QJoinMetaData.makeInferredJoinName(ProcessTrace.TABLE_NAME, ProcessTraceSummaryLine.TABLE_NAME);

         table
            .withIcon(new QIcon().withName("app_registration"))
            .withLabel("Process Trace Summary Line Record")
            .withRecordLabelFormat("%s - %s - %s")
            .withRecordLabelFields("processTraceSummaryLineId", "qqqTableId", "recordId")
            .withSection(new QFieldSection("identity", new QIcon().withName("badge"), Tier.T1, List.of("id", "processTraceSummaryLineId")))
            .withSection(new QFieldSection("data", new QIcon().withName("text_snippet"), Tier.T2, List.of("qqqTableId", "recordId")))
            .withExposedJoin(new ExposedJoin().withLabel("Summary Line").withJoinPath(List.of(parentJoinName)).withJoinTable(ProcessTraceSummaryLine.TABLE_NAME))
            .withExposedJoin(new ExposedJoin().withLabel("Process Trace").withJoinPath(List.of(parentJoinName, grandparentJoinName)).withJoinTable(ProcessTrace.TABLE_NAME));

         table.withCustomizer(TableCustomizers.POST_QUERY_RECORD, new QCodeReference(ProcessTraceSummaryLineRecordIntTableCustomizer.class));
         table.getField("recordId").withFieldAdornment(new FieldAdornment(AdornmentType.LINK).withValue(AdornmentType.LinkValues.TO_RECORD_FROM_TABLE_DYNAMIC, true));

         return (table);
      }
   }



   @QField(isEditable = false, isPrimaryKey = true)
   private Long id;

   @QField(possibleValueSourceName = ProcessTraceSummaryLine.TABLE_NAME)
   private Long processTraceSummaryLineId;

   @QField(label = "Table", possibleValueSourceName = QQQTable.TABLE_NAME)
   private Integer qqqTableId;

   @QField()
   private Integer recordId;



   /*******************************************************************************
    ** Default constructor
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt()
   {
   }



   /*******************************************************************************
    ** Constructor that takes a QRecord
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt(QRecord record)
   {
      populateFromQRecord(record);
   }



   /*******************************************************************************
    ** Getter for id
    *******************************************************************************/
   public Long getId()
   {
      return (this.id);
   }



   /*******************************************************************************
    ** Setter for id
    *******************************************************************************/
   public void setId(Long id)
   {
      this.id = id;
   }



   /*******************************************************************************
    ** Fluent setter for id
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt withId(Long id)
   {
      this.id = id;
      return (this);
   }



   /*******************************************************************************
    ** Getter for processTraceSummaryLineId
    *******************************************************************************/
   public Long getProcessTraceSummaryLineId()
   {
      return (this.processTraceSummaryLineId);
   }



   /*******************************************************************************
    ** Setter for processTraceSummaryLineId
    *******************************************************************************/
   public void setProcessTraceSummaryLineId(Long processTraceSummaryLineId)
   {
      this.processTraceSummaryLineId = processTraceSummaryLineId;
   }



   /*******************************************************************************
    ** Fluent setter for processTraceSummaryLineId
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt withProcessTraceSummaryLineId(Long processTraceSummaryLineId)
   {
      this.processTraceSummaryLineId = processTraceSummaryLineId;
      return (this);
   }



   /*******************************************************************************
    ** Getter for qqqTableId
    *******************************************************************************/
   public Integer getQqqTableId()
   {
      return (this.qqqTableId);
   }



   /*******************************************************************************
    ** Setter for qqqTableId
    *******************************************************************************/
   public void setQqqTableId(Integer qqqTableId)
   {
      this.qqqTableId = qqqTableId;
   }



   /*******************************************************************************
    ** Fluent setter for qqqTableId
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt withQqqTableId(Integer qqqTableId)
   {
      this.qqqTableId = qqqTableId;
      return (this);
   }



   /*******************************************************************************
    ** Getter for recordId
    *******************************************************************************/
   public Integer getRecordId()
   {
      return (this.recordId);
   }



   /*******************************************************************************
    ** Setter for recordId
    *******************************************************************************/
   public void setRecordId(Integer recordId)
   {
      this.recordId = recordId;
   }



   /*******************************************************************************
    ** Fluent setter for recordId
    *******************************************************************************/
   public ProcessTraceSummaryLineRecordInt withRecordId(Integer recordId)
   {
      this.recordId = recordId;
      return (this);
   }

}
