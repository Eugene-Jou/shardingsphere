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
import org.apache.shardingsphere.sql.parser.api.visitor.operation.SQLStatementVisitor;
import org.apache.shardingsphere.sql.parser.api.visitor.ASTNode;
import org.apache.shardingsphere.sql.parser.api.visitor.type.DCLSQLVisitor;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.AlterUserContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CreateUserContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropRoleContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.DropUserContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.GrantContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.ObjectPrivilegeClauseContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.RevokeContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.SetRoleContext;
import org.apache.shardingsphere.sql.parser.sql.common.segment.generic.table.SimpleTableSegment;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengAlterRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengAlterUserStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengCreateRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengCreateUserStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengDropRoleStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengDropUserStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengGrantStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengRevokeStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.dcl.DamengSetRoleStatement;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * DCL Statement SQL visitor for Dameng.
 */
@NoArgsConstructor
public final class DamengDCLStatementSQLVisitor extends DamengStatementSQLVisitor implements DCLSQLVisitor, SQLStatementVisitor {
    
    public DamengDCLStatementSQLVisitor(final Properties props) {
        super(props);
    }
    
    @Override
    public ASTNode visitGrant(final GrantContext ctx) {
        DamengGrantStatement result = new DamengGrantStatement();
        if (null != ctx.objectPrivilegeClause()) {
            for (SimpleTableSegment each : getTableFromPrivilegeClause(ctx.objectPrivilegeClause())) {
                result.getTables().add(each);
            }
        }
        return result;
    }
    
    @Override
    public ASTNode visitRevoke(final RevokeContext ctx) {
        DamengRevokeStatement result = new DamengRevokeStatement();
        if (null != ctx.objectPrivilegeClause()) {
            for (SimpleTableSegment each : getTableFromPrivilegeClause(ctx.objectPrivilegeClause())) {
                result.getTables().add(each);
            }
        }
        return result;
    }
    
    private Collection<SimpleTableSegment> getTableFromPrivilegeClause(final ObjectPrivilegeClauseContext ctx) {
        return null == ctx.onObjectClause().tableName() ? Collections.emptyList() : Collections.singletonList((SimpleTableSegment) visit(ctx.onObjectClause().tableName()));
    }
    
    @Override
    public ASTNode visitCreateUser(final CreateUserContext ctx) {
        return new DamengCreateUserStatement();
    }
    
    @Override
    public ASTNode visitDropUser(final DropUserContext ctx) {
        return new DamengDropUserStatement();
    }
    
    @Override
    public ASTNode visitAlterUser(final AlterUserContext ctx) {
        return new DamengAlterUserStatement();
    }
    
    @Override
    public ASTNode visitCreateRole(final CreateRoleContext ctx) {
        return new DamengCreateRoleStatement();
    }
    
    @Override
    public ASTNode visitAlterRole(final AlterRoleContext ctx) {
        return new DamengAlterRoleStatement();
    }
    
    @Override
    public ASTNode visitDropRole(final DropRoleContext ctx) {
        return new DamengDropRoleStatement();
    }
    
    @Override
    public ASTNode visitSetRole(final SetRoleContext ctx) {
        return new DamengSetRoleStatement();
    }
}
