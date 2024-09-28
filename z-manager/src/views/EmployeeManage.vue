<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;" >
        <el-form label-width="100px" class="demo-ruleForm">
            <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus" style="float: left;margin-bottom: 20px">添加用户</el-button>
        </el-form>

        <el-dialog title="添加用户" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="姓名">
                    <el-input v-model="ruleForm.employeeName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
                    <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model.number="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="ruleForm.email"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="ruleForm.address"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="ruleForm.remark"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="编辑用户" :visible.sync="dialogFormVisible2">
            <el-form :model="ruleFormEdit" ref="ruleFormEdit" label-width="100px" class="demo-ruleForm">
                <el-form-item label="编号">
                    <el-input v-model="ruleFormEdit.employeeId" readOnly></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="ruleFormEdit.employeeName"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio v-model="ruleFormEdit.sex" label="男">男</el-radio>
                    <el-radio v-model="ruleFormEdit.sex" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model.number="ruleFormEdit.phone"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="ruleFormEdit.email"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="ruleFormEdit.address"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="ruleFormEdit.remark"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitFormEdit('ruleFormEdit')">立即修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
        <el-table-column
                fixed
                prop="employeeId"
                label="编号"
                width="100">
        </el-table-column>
        <el-table-column
                prop="employeeName"
                label="姓名"
                width="100">
        </el-table-column>
        <el-table-column
                prop="sex"
                label="性别"
                width="100">
        </el-table-column>
        <el-table-column
                prop="phone"
                label="联系电话"
                width="150">
        </el-table-column>
        <el-table-column
                prop="email"
                label="电子邮箱"
                width="180">
        </el-table-column>
        <el-table-column
                prop="address"
                label="地址"
                width="180">
        </el-table-column>
        <el-table-column
                prop="remark"
                label="备注"
                width="100">
        </el-table-column>
        <el-table-column label="操作" width="280">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="edit(scope.row)">编辑</el-button>
                <el-button
                        size="mini"
                        type="danger"
                        @click="del(scope.row)">删除</el-button>
                <el-button
                        size="mini"
                        type="success"
                        @click="editRole(scope.row)">修改角色</el-button>
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

        <el-dialog title="修改角色" :visible.sync="dialogFormVisible3">
            <el-form ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-select v-model="roleId" placeholder="请选择">
                    <el-option
                            v-for="item in options"
                            :label="item.roleName"
                            :value="item.roleId"
                            :key="item.key">
                    </el-option>
                </el-select>
            </el-form>
            <el-button type="primary" style="margin-top: 20px" @click="updateRole">保存角色</el-button>
        </el-dialog>

    </div>

</template>

<script>
import axios from 'axios';
    export default {
        data() {
            return {
                options: [
                //     {
                //     roleId: '1',
                //     roleName: '员工'
                // }
            ],
                roleId: '',
                employeeId: '',
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3: false,
                ruleForm: {
                    // employeeName: '张三',
                    // sex: '男',
                    // phone:'13378901234',
                    // email: 'lm@163.com',
                    // address: '软件园',
                    // remark: '666'
                },
                ruleFormEdit: '',
                tableData:null,
                pageSize:5,
                total:null,
                currentPage:1
            }
        },
        methods:{
            saveRole(){
                let obj = {
                    roleId: this.roleId,
                    employeeId: this.employeeId
                }
                const _this = this
                axios.post(this.$store.state.globalhost+'roleEmployee/save',obj).then(function (resp) {
                    if(resp.data){
                        _this.$alert('角色添加成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            updateRole(){
                let obj = {
                    roleId: this.roleId,
                    employeeId: this.employeeId
                }
                const _this = this
                axios.put(this.$store.state.globalhost+'roleEmployee/updateRole',obj).then(function (resp) {
                    if(resp.data){
                        _this.$alert('角色修改成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            editRole(row){
                this.employeeId = row.employeeId
                this.dialogFormVisible3 = true
                const _this = this
                axios.get(this.$store.state.globalhost+'role/roleList').then(function (resp) {
                    _this.options = resp.data
                })

                axios.get(this.$store.state.globalhost+'roleEmployee/getRoleByEmployeeId?id='+row.employeeId).then(function (resp) {
                    _this.roleId = resp.data.roleId
                })
            },
            submitForm() {
                const _this = this
                axios.post(this.$store.state.globalhost+'employee/save',_this.ruleForm).then(function (resp) {
                    if(resp.data){
                        _this.$alert(_this.ruleForm.employeeName+'添加成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            submitFormEdit() {
                const _this = this
                axios.put(this.$store.state.globalhost+'employee/update',_this.ruleFormEdit).then(function (resp) {
                    if(resp.data){
                        _this.$alert(_this.ruleFormEdit.employeeName+'修改成功', '', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        });
                    }
                })
            },
            page(currentPage){
                const _this = this
                axios.get(this.$store.state.globalhost+'employee/list?page='+currentPage+'&size='+_this.pageSize).then(function (resp) {
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.total
                })
            },
            edit(row){
                this.ruleFormEdit = {...row}
                this.dialogFormVisible2 = true
            },
            del(row) {
                const _this = this
                this.$confirm('确认删除【'+row.employeeName+'】吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete(this.$store.state.globalhost+'employee/delete?id='+row.employeeId).then(function (resp) {
                        if(resp.data){
                            _this.$alert('【'+row.employeeName+'】已删除', '', {
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
            const _this = this
            axios.get(this.$store.state.globalhost+'employee/list?page=1&size='+_this.pageSize).then(function (resp) {
                if(resp.data.code === -1){
                    _this.$alert(resp.data.msg, '', {
                        confirmButtonText: '确定'
                    });
                }
                _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.total
            })
        }
    }
</script>
