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


import com.kingsrook.qqq.backend.core.processes.tracing.ProcessTracerMessage;


/*******************************************************************************
 **
 *******************************************************************************/
public class ProcessTraceKeyRecordMessage extends ProcessTracerMessage
{
   private final String  tableName;
   private final Integer recordId;



   /***************************************************************************
    **
    ***************************************************************************/
   public ProcessTraceKeyRecordMessage(String tableName, Integer recordId)
   {
      super("Process Key Record is " + tableName + " " + recordId);
      this.tableName = tableName;
      this.recordId = recordId;
   }



   /*******************************************************************************
    ** Getter for tableName
    **
    *******************************************************************************/
   public String getTableName()
   {
      return (tableName);
   }



   /*******************************************************************************
    ** Getter for id
    **
    *******************************************************************************/
   public Integer getRecordId()
   {
      return (recordId);
   }
}
