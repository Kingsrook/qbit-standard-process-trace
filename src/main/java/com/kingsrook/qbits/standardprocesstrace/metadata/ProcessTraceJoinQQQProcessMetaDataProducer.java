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

package com.kingsrook.qbits.standardprocesstrace.metadata;


import com.kingsrook.qbits.standardprocesstrace.model.ProcessTrace;
import com.kingsrook.qqq.backend.core.exceptions.QException;
import com.kingsrook.qqq.backend.core.model.metadata.MetaDataProducer;
import com.kingsrook.qqq.backend.core.model.metadata.QInstance;
import com.kingsrook.qqq.backend.core.model.metadata.joins.JoinOn;
import com.kingsrook.qqq.backend.core.model.metadata.joins.JoinType;
import com.kingsrook.qqq.backend.core.model.metadata.joins.QJoinMetaData;
import com.kingsrook.qqq.backend.core.model.processes.QQQProcess;


/*******************************************************************************
 ** Meta Data Producer for ProcessTraceJoinQQQProcess
 *******************************************************************************/
public class ProcessTraceJoinQQQProcessMetaDataProducer extends MetaDataProducer<QJoinMetaData>
{
   public static final String NAME = "ProcessTraceJoinQQQProcess";



   /*******************************************************************************
    **
    *******************************************************************************/
   @Override
   public QJoinMetaData produce(QInstance qInstance) throws QException
   {
      return (new QJoinMetaData()
         .withLeftTable(ProcessTrace.TABLE_NAME)
         .withRightTable(QQQProcess.TABLE_NAME)
         .withType(JoinType.MANY_TO_ONE)
         .withJoinOn(new JoinOn("qqqProcessId", "id"))
         .withName(NAME)
      );
   }

}
