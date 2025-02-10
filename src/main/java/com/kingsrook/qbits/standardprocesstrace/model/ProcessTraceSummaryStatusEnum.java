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

import java.util.Objects;
import com.kingsrook.qqq.backend.core.model.metadata.possiblevalues.PossibleValueEnum;
import com.kingsrook.qqq.backend.core.model.metadata.producers.annotations.QMetaDataProducingPossibleValueEnum;


/*******************************************************************************
 ** ProcessTraceSummaryStatusEnum - possible value enum
 *******************************************************************************/
@QMetaDataProducingPossibleValueEnum
public enum ProcessTraceSummaryStatusEnum implements PossibleValueEnum<String>
{
   OK("OK"),
   WARNING("Warning"),
   ERROR("Error"),
   INFO("Info");


   private final String label;

   public static final String NAME = "ProcessTraceSummaryStatusEnum";



   /*******************************************************************************
    **
    *******************************************************************************/
   ProcessTraceSummaryStatusEnum(String label)
   {
      this.label = label;
   }



   /*******************************************************************************
    ** Get instance by id
    **
    *******************************************************************************/
   public static ProcessTraceSummaryStatusEnum getById(String id)
   {
      if(id == null)
      {
         return (null);
      }

      for(ProcessTraceSummaryStatusEnum value : ProcessTraceSummaryStatusEnum.values())
      {
         if(Objects.equals(value.name(), id))
         {
            return (value);
         }
      }

      return (null);
   }



   /*******************************************************************************
    **
    *******************************************************************************/
   @Override
   public String getPossibleValueId()
   {
      return (name());
   }



   /*******************************************************************************
    **
    *******************************************************************************/
   @Override
   public String getPossibleValueLabel()
   {
      return (label);
   }
}
