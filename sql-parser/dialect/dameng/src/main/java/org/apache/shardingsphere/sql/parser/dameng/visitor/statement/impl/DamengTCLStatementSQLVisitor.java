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
import org.apache.shardingsphere.sql.parser.api.visitor.type.TCLSQLVisitor;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.CommitContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.RollbackContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.SavepointContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.SetConstraintsContext;
import org.apache.shardingsphere.sql.parser.autogen.DamengStatementParser.SetTransactionContext;
import org.apache.shardingsphere.sql.parser.sql.common.value.identifier.IdentifierValue;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.tcl.DamengCommitStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.tcl.DamengRollbackStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.tcl.DamengSavepointStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.tcl.DamengSetConstraintsStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.dameng.tcl.DamengSetTransactionStatement;

import java.util.Properties;

/**
 * TCL Statement SQL visitor for Dameng.
 */
@NoArgsConstructor
public final class DamengTCLStatementSQLVisitor extends DamengStatementSQLVisitor implements TCLSQLVisitor, SQLStatementVisitor {
    
    public DamengTCLStatementSQLVisitor(final Properties props) {
        super(props);
    }
    
    @Override
    public ASTNode visitSetTransaction(final SetTransactionContext ctx) {
        return new DamengSetTransactionStatement();
    }
    
    @Override
    public ASTNode visitCommit(final CommitContext ctx) {
        return new DamengCommitStatement();
    }
    
    @Override
    public ASTNode visitRollback(final RollbackContext ctx) {
        DamengRollbackStatement result = new DamengRollbackStatement();
        if (null != ctx.savepointClause().savepointName()) {
            result.setSavepointName(((IdentifierValue) visit(ctx.savepointClause().savepointName())).getValue());
        }
        return result;
    }
    
    @Override
    public ASTNode visitSavepoint(final SavepointContext ctx) {
        DamengSavepointStatement result = new DamengSavepointStatement();
        result.setSavepointName(((IdentifierValue) visit(ctx.savepointName())).getValue());
        return result;
    }
    
    @Override
    public ASTNode visitSetConstraints(final SetConstraintsContext ctx) {
        return new DamengSetConstraintsStatement();
    }
}
