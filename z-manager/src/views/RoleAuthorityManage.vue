<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;">
        <el-form label-width="100px" class="demo-ruleForm">
            <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus"
                       style="float: left;margin-bottom: 20px">添加角色
            </el-button>
        </el-form>

        <el-dialog title="添加角色" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="角色名称">
                    <el-input v-model="ruleForm.roleName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="编辑角色" :visible.sync="dialogFormVisible3">
            <el-form :model="ruleFormEdit" ref="ruleFormEdit" label-width="100px" class="demo-ruleForm">
                <el-form-item label="角色名称">
                    <el-input v-model="ruleFormEdit.roleName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitFormEdit('ruleFormEdit')">立即修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="修改权限" :visible.sync="dialogFormVisible2">
            <el-tree
                    :data="data"
                    show-checkbox
                    default-expand-all
                    node-key="id"
                    ref="tree"
                    highlight-current
                    :default-checked-keys="selected">
            </el-tree>
            <el-button type="primary" style="margin-top: 20px" @click="saveAuthority">保存权限</el-button>
        </el-dialog>

        <el-table
                :data="tableData"
                border
                stripe
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="roleId"
                    label="编号"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="roleName"
                    label="角色名称"
                    width="230">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="del(scope.row)">删除
                    </el-button>
                    <el-button
                            size="mini"
                            type="success"
                            @click="editAuthority(scope.row)">修改权限
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 20px;float: right"
                       background
                       layout="prev, pager, next"
                       :page-size="pageSize"
                       :total="total"
                       :current-page.sync="currentPage"
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                selected: [],
                data: [],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3: false,
                roleId: this.roleId,
                ruleForm: {
                    roleName: ''
                },
                ruleFormEdit: '',
                tableData: [
                    // {
                    //     id: 1
                    // }
                ],
                pageSize: 5,
                total: null,
                keyWord: '',
                currentPage: 1,
                dialogTableVisible: false,
                category: '',
                selectedCategory: ''
            }
        },
        methods: {
            editAuthority(row) {
                this.dialogFormVisible2 = true;
                this.roleId = row.roleId;
                const _this = this;

                // 编辑前先设置为空，界面发现有更新残留问题
                _this.selected = [];

                //查询所有的权限数据
                axios.get(this.$store.state.globalhost + 'authority/list').then(function (resp) {
                    _this.data = resp.data
                });
                //查询该角色拥有的权限
                axios.get(this.$store.state.globalhost + 'role/getAuthorityById?id=' + row.roleId).then(function (resp) {
                    _this.selected = resp.data
                })
            },
            saveAuthority() {
                let selected = [];
                let array = this.$refs.tree.getCheckedNodes();
                for (let i = 0; i < array.length; i++) {
                    selected.push(array[i].id)
                }
                selected.push(this.roleId);
                const _this = this;
                axios.put(this.$store.state.globalhost + 'role/updateAuthority', selected).then(function (resp) {
                    if (resp.data) {
                        _this.$alert('权限修改成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            submitForm() {
                const _this = this;
                axios.post(this.$store.state.globalhost + 'role/save', _this.ruleForm).then(function (resp) {
                    if (resp.data) {
                        _this.$alert(_this.ruleForm.roleName + '添加成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            submitFormEdit() {
                const _this = this;
                axios.put(this.$store.state.globalhost + 'role/update', _this.ruleFormEdit).then(function (resp) {
                    if (resp.data) {
                        _this.$alert(_this.ruleFormEdit.roleName + '修改成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            page(currentPage) {
                const _this = this;
                axios.get(this.$store.state.globalhost + 'role/list?page=' + currentPage + '&size=' + _this.pageSize).then(function (resp) {
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.total
                })
            },
            edit(row) {
                this.ruleFormEdit = {...row};
                this.dialogFormVisible3 = true
            },
            del(row) {
                const _this = this;
                this.$confirm('确认删除【' + row.roleName + '】吗？',  '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete(this.$store.state.globalhost + 'role/delete?id=' + row.roleId).then(function (resp) {
                        if (resp.data) {
                            _this.$alert('【' + row.roleName + '】已删除', '', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload()
                                }
                            });
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        created() {
            const _this = this;
            axios.get(this.$store.state.globalhost + 'role/list?page=1&size=' + _this.pageSize).then(function (resp) {
                if (resp.data.code == -1) {
                    _this.$alert(resp.data.msg, '', {
                        confirmButtonText: '确定'
                    });
                }
                _this.tableData = resp.data.content;
                _this.pageSize = resp.data.size;
                _this.total = resp.data.total
            })
        }
    }
</script>
