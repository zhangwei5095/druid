/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.bvt.sql.mysql.create;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.sql.MysqlTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;

public class MySqlCreateTableTest61 extends MysqlTest {

    @Test
    public void test_one() throws Exception {
        String sql = "create table t (pk int auto_increment primary key,a timestamp null default current_timestamp on update current_timestamp, b timestamp null)";

        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseCreateTable();

        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);

        {
            String output = SQLUtils.toMySqlString(stmt);
            Assert.assertEquals("CREATE TABLE t ("
                    + "\n\tpk int PRIMARY KEY AUTO_INCREMENT, "
                    + "\n\ta timestamp NULL DEFAULT current_timestamp ON UPDATE current_timestamp, "
                    + "\n\tb timestamp NULL"
                    + "\n)", output);
        }
        {
            String output = SQLUtils.toMySqlString(stmt, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
            Assert.assertEquals("create table t ("
                    + "\n\tpk int primary key auto_increment, "
                    + "\n\ta timestamp null default current_timestamp on update current_timestamp, "
                    + "\n\tb timestamp null"
                    + "\n)", output);
        }
    }
}
