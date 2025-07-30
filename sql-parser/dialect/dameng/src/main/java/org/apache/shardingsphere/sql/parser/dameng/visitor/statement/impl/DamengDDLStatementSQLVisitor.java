/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.dameng.visitor.statement.impl;

import lombok.NoArgsConstructor;
import org.apache.shardingsphere.sql.parser.api.visitor.ASTNode;
import org.apache.shardingsphere.sql.parser.api.visitor.operation.SQLStatementVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.type.DDLSQLVisitor;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AddColumnSpecificationContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AddConstraintSpecificationContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterAnalyticViewContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterAttributeDimensionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterAuditPolicyContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterClusterContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDatabaseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDatabaseDictionaryContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDatabaseLinkContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDefinitionClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDimensionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterDiskgroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterFlashbackArchiveContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterFunctionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterHierarchyContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterIndexContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterIndexTypeContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterInmemoryJoinGroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterJavaContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterLibraryContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterLockdownProfileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterMaterializedViewContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterMaterializedViewLogContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterMaterializedZonemapContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterOperatorContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterOutlineContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterPackageContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterPluggableDatabaseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterProcedureContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterSequenceContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterSessionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterSynonymContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterSystemContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterTableContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterTriggerContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterViewContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AnalyzeContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AssociateStatisticsContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AuditContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ColumnDefinitionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ColumnNameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ColumnOrVirtualDefinitionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CommentContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ConstraintClausesContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateContextContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateControlFileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDatabaseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDatabaseLinkContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDefinitionClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDimensionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDirectoryContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateDiskgroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateEditionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateFlashbackArchiveContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateFunctionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateIndexContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateInmemoryJoinGroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateLockdownProfileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreatePFileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateProcedureContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateRestorePointContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateRollbackSegmentContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateSPFileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateSequenceContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateSynonymContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateTableContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DisassociateStatisticsContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropColumnSpecificationContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropConstraintClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropDatabaseLinkContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropDimensionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropDirectoryContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropDiskgroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropEditionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropFlashbackArchiveContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropIndexContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropIndexTypeContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropInmemoryJoinGroupContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropJavaContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropLibraryContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropLockdownProfileContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropMaterializedViewContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropMaterializedViewLogContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropMaterializedZonemapContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropOperatorContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropOutlineContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropPackageContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropPluggableDatabaseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropProcedureContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropRestorePointContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropRollbackSegmentContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropSynonymContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropTableContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropTriggerContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropViewContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.FlashbackDatabaseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.FlashbackTableContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.FunctionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.IndexExpressionContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.IndexExpressionsContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.IndexNameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.IndexTypeNameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.InlineConstraintContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ModifyColPropertiesContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ModifyColumnSpecificationContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ModifyConstraintClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.NoAuditContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.OperateColumnClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.OutOfLineConstraintContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.OutOfLineRefConstraintContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.PackageNameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.PurgeContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.RelationalPropertyContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.RenameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.TableNameContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.TruncateTableContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.TypeNameContext;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.AlterDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.CreateDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.column.ColumnDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.column.alter.AddColumnDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.column.alter.DropColumnDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.column.alter.ModifyColumnDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.constraint.ConstraintDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.constraint.ConstraintSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.constraint.alter.AddConstraintDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.constraint.alter.DropConstraintDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.constraint.alter.ModifyConstraintDefinitionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.index.IndexSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.index.IndexTypeSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.packages.PackageSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.ddl.type.TypeSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.column.ColumnSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr.FunctionSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.generic.DataTypeSegment;
import org.apache.shardingsphere.sql.parser.sql.common.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.sql.common.value.collection.CollectionValue;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterAnalyticViewStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterAttributeDimensionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterAuditPolicyStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterClusterStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterDatabaseDictionaryStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterDatabaseLinkStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterDatabaseStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterDimensionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterDiskgroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterFlashbackArchiveStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterFunctionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterHierarchyStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterIndexStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterIndexTypeStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterInmemoryJoinGroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterJavaStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterLibraryStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterLockdownProfileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterMaterializedViewLogStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterMaterializedViewStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterMaterializedZonemapStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterOperatorStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterOutlineStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterPackageStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterPluggableDatabaseStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterSequenceStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterProcedureStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterSessionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterSynonymStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterSystemStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterTableStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterTriggerStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAlterViewStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAnalyzeStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAssociateStatisticsStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengAuditStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCommentStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateContextStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateControlFileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateDatabaseLinkStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateDatabaseStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateDimensionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateDirectoryStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateDiskgroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateEditionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateFlashbackArchiveStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateFunctionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateIndexStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateInmemoryJoinGroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateLockdownProfileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreatePFileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateProcedureStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateRestorePointStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateRollbackSegmentStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateSPFileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateSequenceStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateSynonymStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengCreateTableStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDisassociateStatisticsStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropDatabaseLinkStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropDimensionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropDirectoryStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropDiskgroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropEditionStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropFlashbackArchiveStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropIndexStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropIndexTypeStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropInmemoryJoinGroupStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropJavaStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropLibraryStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropLockdownProfileStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropMaterializedViewLogStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropMaterializedViewStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropMaterializedZonemapStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropOperatorStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropOutlineStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropPackageStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropPluggableDatabaseStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropProcedureStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropRestorePointStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropRollbackSegmentStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropSynonymStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropTableStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropTriggerStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengDropViewStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengFlashbackDatabaseStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengFlashbackTableStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengNoAuditStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengPurgeStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengRenameStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.ddl.DamengTruncateStatement;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;

/**
 * DDL Statement SQL visitor for Dameng.
 */
@NoArgsConstructor
public final class DamengDDLStatementSQLVisitor extends DamengStatementSQLVisitor implements DDLSQLVisitor, SQLStatementVisitor {
    
    public DamengDDLStatementSQLVisitor(final Properties props) {
        super(props);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ASTNode visitCreateTable(final CreateTableContext ctx) {
        DamengCreateTableStatement result = new DamengCreateTableStatement();
        result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        if (null != ctx.createDefinitionClause()) {
            CollectionValue<CreateDefinitionSegment> createDefinitions = (CollectionValue<CreateDefinitionSegment>) visit(ctx.createDefinitionClause());
            for (CreateDefinitionSegment each : createDefinitions.getValue()) {
                if (each instanceof ColumnDefinitionSegment) {
                    result.getColumnDefinitions().add((ColumnDefinitionSegment) each);
                } else if (each instanceof ConstraintDefinitionSegment) {
                    result.getConstraintDefinitions().add((ConstraintDefinitionSegment) each);
                }
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitCreateDefinitionClause(final CreateDefinitionClauseContext ctx) {
        CollectionValue<CreateDefinitionSegment> result = new CollectionValue<>();
        if (null == ctx.createRelationalTableClause()) {
            return result;
        }
        for (RelationalPropertyContext each : ctx.createRelationalTableClause().relationalProperties().relationalProperty()) {
            if (null != each.columnDefinition()) {
                result.getValue().add((ColumnDefinitionSegment) visit(each.columnDefinition()));
            }
            if (null != each.outOfLineConstraint()) {
                result.getValue().add((ConstraintDefinitionSegment) visit(each.outOfLineConstraint()));
            }
            if (null != each.outOfLineRefConstraint()) {
                result.getValue().add((ConstraintDefinitionSegment) visit(each.outOfLineRefConstraint()));
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitColumnDefinition(final ColumnDefinitionContext ctx) {
        ColumnSegment column = (ColumnSegment) visit(ctx.columnName());
        DataTypeSegment dataType = (DataTypeSegment) visit(ctx.dataType());
        boolean isPrimaryKey = ctx.inlineConstraint().stream().anyMatch(each -> null != each.primaryKey());
        boolean isNotNull = ctx.inlineConstraint().stream().anyMatch(each -> null != each.NOT() && null != each.NULL());
        ColumnDefinitionSegment result = new ColumnDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), column, dataType, isPrimaryKey, isNotNull);
        for (InlineConstraintContext each : ctx.inlineConstraint()) {
            if (null != each.referencesClause()) {
                result.getReferencedTables().add((SimpleTableSegment) visit(each.referencesClause().tableName()));
            }
        }
        if (null != ctx.inlineRefConstraint()) {
            result.getReferencedTables().add((SimpleTableSegment) visit(ctx.inlineRefConstraint().tableName()));
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ASTNode visitOutOfLineConstraint(final OutOfLineConstraintContext ctx) {
        ConstraintDefinitionSegment result = new ConstraintDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
        if (null != ctx.constraintName()) {
            result.setConstraintName((ConstraintSegment) visit(ctx.constraintName()));
        }
        if (null != ctx.primaryKey()) {
            result.getPrimaryKeyColumns().addAll(((CollectionValue<ColumnSegment>) visit(ctx.columnNames())).getValue());
        }
        if (null != ctx.UNIQUE()) {
            result.getIndexColumns().addAll(((CollectionValue<ColumnSegment>) visit(ctx.columnNames())).getValue());
        }
        if (null != ctx.referencesClause()) {
            result.setReferencedTable((SimpleTableSegment) visit(ctx.referencesClause().tableName()));
        }
        return result;
    }
    
    @Override
    public ASTNode visitOutOfLineRefConstraint(final OutOfLineRefConstraintContext ctx) {
        ConstraintDefinitionSegment result = new ConstraintDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
        if (null != ctx.constraintName()) {
            result.setConstraintName((ConstraintSegment) visit(ctx.constraintName()));
        }
        if (null != ctx.referencesClause()) {
            result.setReferencedTable((SimpleTableSegment) visit(ctx.referencesClause().tableName()));
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ASTNode visitAlterTable(final AlterTableContext ctx) {
        DamengAlterTableStatement result = new DamengAlterTableStatement();
        result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        if (null != ctx.alterDefinitionClause()) {
            for (AlterDefinitionSegment each : ((CollectionValue<AlterDefinitionSegment>) visit(ctx.alterDefinitionClause())).getValue()) {
                if (each instanceof AddColumnDefinitionSegment) {
                    result.getAddColumnDefinitions().add((AddColumnDefinitionSegment) each);
                } else if (each instanceof ModifyColumnDefinitionSegment) {
                    result.getModifyColumnDefinitions().add((ModifyColumnDefinitionSegment) each);
                } else if (each instanceof DropColumnDefinitionSegment) {
                    result.getDropColumnDefinitions().add((DropColumnDefinitionSegment) each);
                } else if (each instanceof AddConstraintDefinitionSegment) {
                    result.getAddConstraintDefinitions().add((AddConstraintDefinitionSegment) each);
                } else if (each instanceof ModifyConstraintDefinitionSegment) {
                    result.getModifyConstraintDefinitions().add((ModifyConstraintDefinitionSegment) each);
                } else if (each instanceof DropConstraintDefinitionSegment) {
                    result.getDropConstraintDefinitions().add((DropConstraintDefinitionSegment) each);
                }
            }
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ASTNode visitAlterDefinitionClause(final AlterDefinitionClauseContext ctx) {
        CollectionValue<AlterDefinitionSegment> result = new CollectionValue<>();
        if (null != ctx.columnClauses()) {
            for (OperateColumnClauseContext each : ctx.columnClauses().operateColumnClause()) {
                if (null != each.addColumnSpecification()) {
                    result.getValue().addAll(((CollectionValue<AddColumnDefinitionSegment>) visit(each.addColumnSpecification())).getValue());
                }
                if (null != each.modifyColumnSpecification()) {
                    result.getValue().add((ModifyColumnDefinitionSegment) visit(each.modifyColumnSpecification()));
                }
                if (null != each.dropColumnClause()) {
                    result.getValue().add((DropColumnDefinitionSegment) visit(each.dropColumnClause()));
                }
            }
        }
        if (null != ctx.constraintClauses()) {
            // TODO Support rename constraint
            ConstraintClausesContext constraintClausesContext = ctx.constraintClauses();
            if (null != constraintClausesContext.addConstraintSpecification()) {
                result.combine((CollectionValue<AlterDefinitionSegment>) visit(constraintClausesContext.addConstraintSpecification()));
            }
            if (null != constraintClausesContext.modifyConstraintClause()) {
                result.getValue().add((AlterDefinitionSegment) visit(constraintClausesContext.modifyConstraintClause()));
            }
            for (DropConstraintClauseContext each : constraintClausesContext.dropConstraintClause()) {
                if (null != each.constraintName()) {
                    result.getValue().add((AlterDefinitionSegment) visit(each));
                }
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitAddColumnSpecification(final AddColumnSpecificationContext ctx) {
        CollectionValue<AddColumnDefinitionSegment> result = new CollectionValue<>();
        for (ColumnOrVirtualDefinitionContext each : ctx.columnOrVirtualDefinitions().columnOrVirtualDefinition()) {
            if (null != each.columnDefinition()) {
                AddColumnDefinitionSegment addColumnDefinition = new AddColumnDefinitionSegment(
                        each.columnDefinition().getStart().getStartIndex(), each.columnDefinition().getStop().getStopIndex(),
                        Collections.singletonList((ColumnDefinitionSegment) visit(each.columnDefinition())));
                result.getValue().add(addColumnDefinition);
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitModifyColumnSpecification(final ModifyColumnSpecificationContext ctx) {
        // TODO handle no columnDefinition and multiple columnDefinitions
        ColumnDefinitionSegment columnDefinition = null;
        for (ModifyColPropertiesContext each : ctx.modifyColProperties()) {
            columnDefinition = (ColumnDefinitionSegment) visit(each);
        }
        return new ModifyColumnDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), columnDefinition);
    }
    
    @Override
    public ASTNode visitModifyColProperties(final ModifyColPropertiesContext ctx) {
        ColumnSegment column = (ColumnSegment) visit(ctx.columnName());
        DataTypeSegment dataType = (DataTypeSegment) visit(ctx.dataType());
        // TODO visit pk and reference table
        return new ColumnDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), column, dataType, false, false);
    }
    
    @Override
    public ASTNode visitDropColumnSpecification(final DropColumnSpecificationContext ctx) {
        Collection<ColumnSegment> columns = new LinkedList<>();
        if (null != ctx.columnOrColumnList().columnName()) {
            columns.add((ColumnSegment) visit(ctx.columnOrColumnList().columnName()));
        } else {
            for (ColumnNameContext each : ctx.columnOrColumnList().columnNames().columnName()) {
                columns.add((ColumnSegment) visit(each));
            }
        }
        return new DropColumnDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), columns);
    }
    
    @Override
    public ASTNode visitAddConstraintSpecification(final AddConstraintSpecificationContext ctx) {
        CollectionValue<AddConstraintDefinitionSegment> result = new CollectionValue<>();
        for (OutOfLineConstraintContext each : ctx.outOfLineConstraint()) {
            result.getValue().add(new AddConstraintDefinitionSegment(each.getStart().getStartIndex(), each.getStop().getStopIndex(), (ConstraintDefinitionSegment) visit(each)));
        }
        if (null != ctx.outOfLineRefConstraint()) {
            result.getValue().add(new AddConstraintDefinitionSegment(ctx.outOfLineRefConstraint().getStart().getStartIndex(), ctx.outOfLineRefConstraint().getStop().getStopIndex(),
                    (ConstraintDefinitionSegment) visit(ctx.outOfLineRefConstraint())));
        }
        return result;
    }
    
    @Override
    public ASTNode visitModifyConstraintClause(final ModifyConstraintClauseContext ctx) {
        return new ModifyConstraintDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(),
                (ConstraintSegment) visit(ctx.constraintOption().constraintWithName().constraintName()));
    }
    
    @Override
    public ASTNode visitDropConstraintClause(final DropConstraintClauseContext ctx) {
        return new DropConstraintDefinitionSegment(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex(), (ConstraintSegment) visit(ctx.constraintName()));
    }
    
    @Override
    public ASTNode visitDropTable(final DropTableContext ctx) {
        DamengDropTableStatement result = new DamengDropTableStatement();
        result.getTables().add((SimpleTableSegment) visit(ctx.tableName()));
        return result;
    }
    
    @Override
    public ASTNode visitDropDatabaseLink(final DropDatabaseLinkContext ctx) {
        return new DamengDropDatabaseLinkStatement();
    }
    
    @Override
    public ASTNode visitAlterDatabaseLink(final AlterDatabaseLinkContext ctx) {
        return new DamengAlterDatabaseLinkStatement();
    }
    
    @Override
    public ASTNode visitAlterDatabaseDictionary(final AlterDatabaseDictionaryContext ctx) {
        return new DamengAlterDatabaseDictionaryStatement();
    }
    
    @Override
    public ASTNode visitAlterView(final AlterViewContext ctx) {
        DamengAlterViewStatement result = new DamengAlterViewStatement();
        result.setView((SimpleTableSegment) visit(ctx.viewName()));
        return result;
    }
    
    @Override
    public ASTNode visitDropPackage(final DropPackageContext ctx) {
        return new DamengDropPackageStatement();
    }
    
    @Override
    public ASTNode visitAlterPackage(final AlterPackageContext ctx) {
        return new DamengAlterPackageStatement();
    }
    
    @Override
    public ASTNode visitCreateSynonym(final CreateSynonymContext ctx) {
        return new DamengCreateSynonymStatement();
    }
    
    @Override
    public ASTNode visitDropSynonym(final DropSynonymContext ctx) {
        return new DamengDropSynonymStatement();
    }
    
    @Override
    public ASTNode visitCreateDirectory(final CreateDirectoryContext ctx) {
        return new DamengCreateDirectoryStatement();
    }
    
    @Override
    public ASTNode visitDropView(final DropViewContext ctx) {
        DamengDropViewStatement result = new DamengDropViewStatement();
        result.getViews().add((SimpleTableSegment) visit(ctx.viewName()));
        return result;
    }
    
    @Override
    public ASTNode visitCreateEdition(final CreateEditionContext ctx) {
        return new DamengCreateEditionStatement();
    }
    
    @Override
    public ASTNode visitDropTrigger(final DropTriggerContext ctx) {
        return new DamengDropTriggerStatement();
    }
    
    @Override
    public ASTNode visitAlterTrigger(final AlterTriggerContext ctx) {
        return new DamengAlterTriggerStatement();
    }
    
    @Override
    public ASTNode visitTruncateTable(final TruncateTableContext ctx) {
        DamengTruncateStatement result = new DamengTruncateStatement();
        result.getTables().add((SimpleTableSegment) visit(ctx.tableName()));
        return result;
    }
    
    @Override
    public ASTNode visitCreateIndex(final CreateIndexContext ctx) {
        DamengCreateIndexStatement result = new DamengCreateIndexStatement();
        if (null != ctx.createIndexDefinitionClause().tableIndexClause()) {
            result.setTable((SimpleTableSegment) visit(ctx.createIndexDefinitionClause().tableIndexClause().tableName()));
            result.getColumns().addAll(((CollectionValue) visit(ctx.createIndexDefinitionClause().tableIndexClause().indexExpressions())).getValue());
        }
        result.setIndex((IndexSegment) visit(ctx.indexName()));
        return result;
    }
    
    @Override
    public ASTNode visitIndexExpressions(final IndexExpressionsContext ctx) {
        CollectionValue<ColumnSegment> result = new CollectionValue<>();
        for (IndexExpressionContext each : ctx.indexExpression()) {
            ASTNode astNode = visit(each);
            if (astNode instanceof ColumnSegment) {
                result.getValue().add((ColumnSegment) astNode);
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitIndexExpression(final IndexExpressionContext ctx) {
        return null != ctx.expr() ? visit(ctx.expr()) : visit(ctx.columnName());
    }
    
    @Override
    public ASTNode visitAlterIndex(final AlterIndexContext ctx) {
        DamengAlterIndexStatement result = new DamengAlterIndexStatement();
        result.setIndex((IndexSegment) visit(ctx.indexName()));
        return result;
    }
    
    @Override
    public ASTNode visitDropIndex(final DropIndexContext ctx) {
        DamengDropIndexStatement result = new DamengDropIndexStatement();
        result.getIndexes().add((IndexSegment) visit(ctx.indexName()));
        return result;
    }
    
    @Override
    public ASTNode visitAlterSynonym(final AlterSynonymContext ctx) {
        return new DamengAlterSynonymStatement();
    }
    
    @Override
    public ASTNode visitAlterSession(final AlterSessionContext ctx) {
        return new DamengAlterSessionStatement();
    }
    
    @Override
    public ASTNode visitAlterDatabase(final AlterDatabaseContext ctx) {
        return new DamengAlterDatabaseStatement();
    }
    
    @Override
    public ASTNode visitAlterSystem(final AlterSystemContext ctx) {
        return new DamengAlterSystemStatement();
    }
    
    @Override
    public ASTNode visitAnalyze(final AnalyzeContext ctx) {
        DamengAnalyzeStatement result = new DamengAnalyzeStatement();
        if (null != ctx.tableName()) {
            result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        }
        if (null != ctx.indexName()) {
            result.setIndex((IndexSegment) visit(ctx.indexName()));
        }
        return result;
    }
    
    @Override
    public ASTNode visitAssociateStatistics(final AssociateStatisticsContext ctx) {
        DamengAssociateStatisticsStatement result = new DamengAssociateStatisticsStatement();
        if (null != ctx.columnAssociation()) {
            for (TableNameContext each : ctx.columnAssociation().tableName()) {
                result.getTables().add((SimpleTableSegment) visit(each));
            }
            for (ColumnNameContext each : ctx.columnAssociation().columnName()) {
                result.getColumns().add((ColumnSegment) visit(each));
            }
        }
        if (null != ctx.functionAssociation()) {
            for (IndexNameContext each : ctx.functionAssociation().indexName()) {
                result.getIndexes().add((IndexSegment) visit(each));
            }
            for (FunctionContext each : ctx.functionAssociation().function()) {
                result.getFunctions().add((FunctionSegment) visit(each));
            }
            for (PackageNameContext each : ctx.functionAssociation().packageName()) {
                result.getPackages().add((PackageSegment) visit(each));
            }
            for (TypeNameContext each : ctx.functionAssociation().typeName()) {
                result.getTypes().add((TypeSegment) visit(each));
            }
            for (IndexTypeNameContext each : ctx.functionAssociation().indexTypeName()) {
                result.getIndexTypes().add((IndexTypeSegment) visit(each));
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitDisassociateStatistics(final DisassociateStatisticsContext ctx) {
        DamengDisassociateStatisticsStatement result = new DamengDisassociateStatisticsStatement();
        if (null != ctx.tableName()) {
            for (TableNameContext each : ctx.tableName()) {
                result.getTables().add((SimpleTableSegment) visit(each));
            }
            for (ColumnNameContext each : ctx.columnName()) {
                result.getColumns().add((ColumnSegment) visit(each));
            }
        }
        if (null != ctx.indexName()) {
            for (IndexNameContext each : ctx.indexName()) {
                result.getIndexes().add((IndexSegment) visit(each));
            }
        }
        if (null != ctx.function()) {
            for (FunctionContext each : ctx.function()) {
                result.getFunctions().add((FunctionSegment) visit(each));
            }
        }
        if (null != ctx.packageName()) {
            for (PackageNameContext each : ctx.packageName()) {
                result.getPackages().add((PackageSegment) visit(each));
            }
        }
        if (null != ctx.typeName()) {
            for (TypeNameContext each : ctx.typeName()) {
                result.getTypes().add((TypeSegment) visit(each));
            }
        }
        if (null != ctx.indexTypeName()) {
            for (IndexTypeNameContext each : ctx.indexTypeName()) {
                result.getIndexTypes().add((IndexTypeSegment) visit(each));
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitAudit(final AuditContext ctx) {
        return new DamengAuditStatement();
    }
    
    @Override
    public ASTNode visitNoAudit(final NoAuditContext ctx) {
        return new DamengNoAuditStatement();
    }
    
    @Override
    public ASTNode visitComment(final CommentContext ctx) {
        DamengCommentStatement result = new DamengCommentStatement();
        if (null != ctx.tableName()) {
            result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        }
        if (null != ctx.columnName()) {
            result.setColumn((ColumnSegment) visit(ctx.columnName()));
        }
        if (null != ctx.indexTypeName()) {
            result.setIndexType((IndexTypeSegment) visit(ctx.indexTypeName()));
        }
        return result;
    }
    
    @Override
    public ASTNode visitFlashbackDatabase(final FlashbackDatabaseContext ctx) {
        return new DamengFlashbackDatabaseStatement();
    }
    
    @Override
    public ASTNode visitFlashbackTable(final FlashbackTableContext ctx) {
        DamengFlashbackTableStatement result = new DamengFlashbackTableStatement();
        result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        
        if (null != ctx.renameToTable()) {
            result.setRenameTable((SimpleTableSegment) visit(ctx.renameToTable().tableName()));
        }
        return result;
    }
    
    @Override
    public ASTNode visitPurge(final PurgeContext ctx) {
        DamengPurgeStatement result = new DamengPurgeStatement();
        if (null != ctx.tableName()) {
            result.setTable((SimpleTableSegment) visit(ctx.tableName()));
        }
        if (null != ctx.indexName()) {
            result.setIndex((IndexSegment) visit(ctx.indexName()));
        }
        return result;
    }
    
    @Override
    public ASTNode visitRename(final RenameContext ctx) {
        return new DamengRenameStatement();
    }
    
    @Override
    public ASTNode visitCreateDatabase(final CreateDatabaseContext ctx) {
        return new DamengCreateDatabaseStatement();
    }
    
    @Override
    public ASTNode visitCreateDatabaseLink(final CreateDatabaseLinkContext ctx) {
        return new DamengCreateDatabaseLinkStatement();
    }
    
    @Override
    public ASTNode visitCreateDimension(final CreateDimensionContext ctx) {
        return new DamengCreateDimensionStatement();
    }
    
    @Override
    public ASTNode visitAlterDimension(final AlterDimensionContext ctx) {
        return new DamengAlterDimensionStatement();
    }
    
    @Override
    public ASTNode visitDropDimension(final DropDimensionContext ctx) {
        return new DamengDropDimensionStatement();
    }
    
    @Override
    public ASTNode visitDropDirectory(final DropDirectoryContext ctx) {
        return new DamengDropDirectoryStatement();
    }
    
    @Override
    public ASTNode visitCreateFunction(final CreateFunctionContext ctx) {
        return new DamengCreateFunctionStatement();
    }
    
    @Override
    public ASTNode visitDropEdition(final DropEditionContext ctx) {
        return new DamengDropEditionStatement();
    }
    
    @Override
    public ASTNode visitDropOutline(final DropOutlineContext ctx) {
        return new DamengDropOutlineStatement();
    }
    
    @Override
    public ASTNode visitAlterOutline(final AlterOutlineContext ctx) {
        return new DamengAlterOutlineStatement();
    }
    
    @Override
    public ASTNode visitAlterAnalyticView(final AlterAnalyticViewContext ctx) {
        return new DamengAlterAnalyticViewStatement();
    }
    
    @Override
    public ASTNode visitAlterAttributeDimension(final AlterAttributeDimensionContext ctx) {
        return new DamengAlterAttributeDimensionStatement();
    }
    
    @Override
    public ASTNode visitCreateSequence(final CreateSequenceContext ctx) {
        return new DamengCreateSequenceStatement();
    }
    
    @Override
    public ASTNode visitAlterSequence(final AlterSequenceContext ctx) {
        return new DamengAlterSequenceStatement();
    }
    
    @Override
    public ASTNode visitCreateContext(final CreateContextContext ctx) {
        return new DamengCreateContextStatement();
    }
    
    @Override
    public ASTNode visitCreateSPFile(final CreateSPFileContext ctx) {
        return new DamengCreateSPFileStatement();
    }
    
    @Override
    public ASTNode visitCreatePFile(final CreatePFileContext ctx) {
        return new DamengCreatePFileStatement();
    }
    
    @Override
    public ASTNode visitCreateControlFile(final CreateControlFileContext ctx) {
        return new DamengCreateControlFileStatement();
    }
    
    @Override
    public ASTNode visitCreateFlashbackArchive(final CreateFlashbackArchiveContext ctx) {
        return new DamengCreateFlashbackArchiveStatement();
    }
    
    @Override
    public ASTNode visitAlterFlashbackArchive(final AlterFlashbackArchiveContext ctx) {
        return new DamengAlterFlashbackArchiveStatement();
    }
    
    @Override
    public ASTNode visitDropFlashbackArchive(final DropFlashbackArchiveContext ctx) {
        return new DamengDropFlashbackArchiveStatement();
    }
    
    @Override
    public ASTNode visitCreateDiskgroup(final CreateDiskgroupContext ctx) {
        return new DamengCreateDiskgroupStatement();
    }
    
    @Override
    public ASTNode visitDropDiskgroup(final DropDiskgroupContext ctx) {
        return new DamengDropDiskgroupStatement();
    }
    
    @Override
    public ASTNode visitCreateRollbackSegment(final CreateRollbackSegmentContext ctx) {
        return new DamengCreateRollbackSegmentStatement();
    }
    
    @Override
    public ASTNode visitDropRollbackSegment(final DropRollbackSegmentContext ctx) {
        return new DamengDropRollbackSegmentStatement();
    }
    
    @Override
    public ASTNode visitCreateLockdownProfile(final CreateLockdownProfileContext ctx) {
        return new DamengCreateLockdownProfileStatement();
    }
    
    @Override
    public ASTNode visitDropLockdownProfile(final DropLockdownProfileContext ctx) {
        return new DamengDropLockdownProfileStatement();
    }
    
    @Override
    public ASTNode visitCreateInmemoryJoinGroup(final CreateInmemoryJoinGroupContext ctx) {
        return new DamengCreateInmemoryJoinGroupStatement();
    }
    
    @Override
    public ASTNode visitAlterInmemoryJoinGroup(final AlterInmemoryJoinGroupContext ctx) {
        return new DamengAlterInmemoryJoinGroupStatement();
    }
    
    @Override
    public ASTNode visitDropInmemoryJoinGroup(final DropInmemoryJoinGroupContext ctx) {
        return new DamengDropInmemoryJoinGroupStatement();
    }
    
    @Override
    public ASTNode visitCreateRestorePoint(final CreateRestorePointContext ctx) {
        return new DamengCreateRestorePointStatement();
    }
    
    @Override
    public ASTNode visitDropRestorePoint(final DropRestorePointContext ctx) {
        return new DamengDropRestorePointStatement();
    }
    
    @Override
    public ASTNode visitAlterOperator(final AlterOperatorContext ctx) {
        return new DamengAlterOperatorStatement();
    }
    
    @Override
    public ASTNode visitDropOperator(final DropOperatorContext ctx) {
        return new DamengDropOperatorStatement();
    }
    
    @Override
    public ASTNode visitAlterLibrary(final AlterLibraryContext ctx) {
        return new DamengAlterLibraryStatement();
    }
    
    @Override
    public ASTNode visitAlterMaterializedZonemap(final AlterMaterializedZonemapContext ctx) {
        return new DamengAlterMaterializedZonemapStatement();
    }
    
    @Override
    public ASTNode visitAlterJava(final AlterJavaContext ctx) {
        return new DamengAlterJavaStatement();
    }
    
    @Override
    public ASTNode visitAlterAuditPolicy(final AlterAuditPolicyContext ctx) {
        return new DamengAlterAuditPolicyStatement();
    }
    
    @Override
    public ASTNode visitAlterCluster(final AlterClusterContext ctx) {
        return new DamengAlterClusterStatement();
    }
    
    @Override
    public ASTNode visitAlterDiskgroup(final AlterDiskgroupContext ctx) {
        return new DamengAlterDiskgroupStatement();
    }
    
    @Override
    public ASTNode visitAlterIndexType(final AlterIndexTypeContext ctx) {
        return new DamengAlterIndexTypeStatement();
    }
    
    @Override
    public ASTNode visitAlterMaterializedView(final AlterMaterializedViewContext ctx) {
        return new DamengAlterMaterializedViewStatement();
    }
    
    @Override
    public ASTNode visitAlterMaterializedViewLog(final AlterMaterializedViewLogContext ctx) {
        return new DamengAlterMaterializedViewLogStatement();
    }
    
    @Override
    public ASTNode visitAlterFunction(final AlterFunctionContext ctx) {
        return new DamengAlterFunctionStatement();
    }
    
    @Override
    public ASTNode visitAlterHierarchy(final AlterHierarchyContext ctx) {
        return new DamengAlterHierarchyStatement();
    }
    
    @Override
    public ASTNode visitAlterLockdownProfile(final AlterLockdownProfileContext ctx) {
        return new DamengAlterLockdownProfileStatement();
    }
    
    @Override
    public ASTNode visitAlterPluggableDatabase(final AlterPluggableDatabaseContext ctx) {
        return new DamengAlterPluggableDatabaseStatement();
    }
    
    @Override
    public ASTNode visitCreateProcedure(final CreateProcedureContext ctx) {
        return new DamengCreateProcedureStatement();
    }
    
    @Override
    public ASTNode visitAlterProcedure(final AlterProcedureContext ctx) {
        return new DamengAlterProcedureStatement();
    }
    
    @Override
    public ASTNode visitDropProcedure(final DropProcedureContext ctx) {
        return new DamengDropProcedureStatement();
    }
    
    @Override
    public ASTNode visitDropIndexType(final DropIndexTypeContext ctx) {
        return new DamengDropIndexTypeStatement();
    }
    
    @Override
    public ASTNode visitDropPluggableDatabase(final DropPluggableDatabaseContext ctx) {
        return new DamengDropPluggableDatabaseStatement();
    }
    
    @Override
    public ASTNode visitDropJava(final DropJavaContext ctx) {
        return new DamengDropJavaStatement();
    }
    
    @Override
    public ASTNode visitDropLibrary(final DropLibraryContext ctx) {
        return new DamengDropLibraryStatement();
    }
    
    @Override
    public ASTNode visitDropMaterializedView(final DropMaterializedViewContext ctx) {
        return new DamengDropMaterializedViewStatement();
    }
    
    @Override
    public ASTNode visitDropMaterializedViewLog(final DropMaterializedViewLogContext ctx) {
        return new DamengDropMaterializedViewLogStatement();
    }
    
    @Override
    public ASTNode visitDropMaterializedZonemap(final DropMaterializedZonemapContext ctx) {
        return new DamengDropMaterializedZonemapStatement();
    }
}
