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


/*******************************************************************************
 ** ad-hoc Configuration data for this qbit.
 ** TODO - when qbit configs become formalized in qqq, adapt this qbit to that interface.
 *******************************************************************************/
public class StandardProcessTraceQBitConfig
{
   private String userIdPossibleValueSourceName;
   private String userTableName;
   private String userIdReferenceFieldName;



   /*******************************************************************************
    ** Getter for userTableName
    *******************************************************************************/
   public String getUserTableName()
   {
      return (this.userTableName);
   }



   /*******************************************************************************
    ** Setter for userTableName
    *******************************************************************************/
   public void setUserTableName(String userTableName)
   {
      this.userTableName = userTableName;
   }



   /*******************************************************************************
    ** Fluent setter for userTableName
    *******************************************************************************/
   public StandardProcessTraceQBitConfig withUserTableName(String userTableName)
   {
      this.userTableName = userTableName;
      return (this);
   }


   /*******************************************************************************
    ** Getter for userIdReferenceFieldName
    *******************************************************************************/
   public String getUserIdReferenceFieldName()
   {
      return (this.userIdReferenceFieldName);
   }



   /*******************************************************************************
    ** Setter for userIdReferenceFieldName
    *******************************************************************************/
   public void setUserIdReferenceFieldName(String userIdReferenceFieldName)
   {
      this.userIdReferenceFieldName = userIdReferenceFieldName;
   }



   /*******************************************************************************
    ** Fluent setter for userIdReferenceFieldName
    *******************************************************************************/
   public StandardProcessTraceQBitConfig withUserIdReferenceFieldName(String userIdReferenceFieldName)
   {
      this.userIdReferenceFieldName = userIdReferenceFieldName;
      return (this);
   }



   /*******************************************************************************
    ** Getter for userIdPossibleValueSourceName
    *******************************************************************************/
   public String getUserIdPossibleValueSourceName()
   {
      return (this.userIdPossibleValueSourceName);
   }



   /*******************************************************************************
    ** Setter for userIdPossibleValueSourceName
    *******************************************************************************/
   public void setUserIdPossibleValueSourceName(String userIdPossibleValueSourceName)
   {
      this.userIdPossibleValueSourceName = userIdPossibleValueSourceName;
   }



   /*******************************************************************************
    ** Fluent setter for userIdPossibleValueSourceName
    *******************************************************************************/
   public StandardProcessTraceQBitConfig withUserIdPossibleValueSourceName(String userIdPossibleValueSourceName)
   {
      this.userIdPossibleValueSourceName = userIdPossibleValueSourceName;
      return (this);
   }


}
