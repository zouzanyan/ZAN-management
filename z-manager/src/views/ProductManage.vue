<template>
    <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;" >
        <el-form label-width="100px" class="demo-ruleForm">
            <el-button @click="dialogFormVisible = true" type="primary" icon="el-icon-plus" style="float: left;margin-bottom: 20px">添加货物</el-button>
        </el-form>

        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
        <el-table-column
                fixed
                prop="productId"
                label="商品编号"
                width="100">
        </el-table-column>
            <el-table-column
                    fixed
                    prop="productName"
                    label="商品名称"
                    width="200">
            </el-table-column>
        <el-table-column
                prop="productPrice"
                label="商品价格"
                width="100">
        </el-table-column>
            <el-table-column
                    prop="productStock"
                    label="商品库存"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="productDescription"
                    label="商品描述"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="categoryName"
                    label="分类名称"
                    width="">
            </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="edit(scope.row)">编辑</el-button>
                <el-button
                        size="mini"
                        type="danger"
                        @click="del(scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>


        <el-dialog title="添加货物" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="货物名称">
                    <el-input v-model="ruleForm.productName"></el-input>
                </el-form-item>
                <el-form-item label="货物价格">
                    <el-input v-model="ruleForm.productPrice"></el-input>
                </el-form-item>
                <el-form-item label="货物库存">
                    <el-input v-model="ruleForm.productStock"></el-input>
                </el-form-item>
                <el-form-item label="货物描述">
                    <el-input v-model="ruleForm.productDescription"></el-input>
                </el-form-item>
                <el-form-item label="货物分类">
                    <el-select style="" v-model="ruleForm.categoryId" placeholder="请选择">
                        <el-option
                                v-for="item in categoryList"
                                :label="item.categoryName"
                                :value="item.categoryId"
                                :key="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="编辑分类" :visible.sync="dialogFormVisible2">
            <el-form :model="ruleFormEdit" ref="ruleFormEdit" label-width="100px" class="demo-ruleForm">
                <el-form-item label="货物编号">
                    <el-input v-model="ruleFormEdit.productId" readOnly></el-input>
                </el-form-item>
                <el-form-item label="货物名称">
                    <el-input v-model="ruleFormEdit.productName"></el-input>
                </el-form-item>
                <el-form-item label="货物价格">
                    <el-input v-model="ruleFormEdit.productPrice"></el-input>
                </el-form-item>
                <el-form-item label="货物库存">
                    <el-input v-model="ruleFormEdit.productStock"></el-input>
                </el-form-item>
                <el-form-item label="货物描述">
                    <el-input v-model="ruleFormEdit.productDescription"></el-input>
                </el-form-item>
                <el-form-item label="货物分类">
                    <el-select style="" v-model="ruleFormEdit.categoryId" placeholder="请选择">
                        <el-option
                                v-for="item in categoryList"
                                :label="item.categoryName"
                                :value="item.categoryId"
                                :key="item.key">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitFormEdit('ruleFormEdit')">立即修改</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>


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
                categoryList: [
                    // {
                    //     categoryId: 1,
                    //     categoryName: '商品1'
                    // },
                    // {
                    //     categoryId: 2,
                    //     categoryName: '商品2'
                    // }
                ],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                ruleForm: {
                    // productName: '商品1',
                    // productPrice: 100,
                    // productStock: 50,
                    // productDescription: '好好好',
                    // categoryId: 1
                },
                ruleFormEdit: '',
                tableData:null,
                pageSize:3,
                total:null,
                currentPage:1
            }
        },
        methods:{
            submitForm() {
                const _this = this
                axios.post(this.$store.state.globalhost+'productInfo/save',_this.ruleForm).then(function (resp) {
                    if(resp.data){
                        _this.$alert(_this.ruleForm.productName+'添加成功', '', {
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
                axios.put(this.$store.state.globalhost+'productInfo/update',_this.ruleFormEdit).then(function (resp) {
                    if(resp.data){
                        _this.$alert(_this.ruleFormEdit.productName+'修改成功', '', {
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
                axios.get(this.$store.state.globalhost+'productInfo/list?page='+currentPage+'&size='+_this.pageSize).then(function (resp) {
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
                this.$confirm('确认删除【'+row.productName+'】吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete(this.$store.state.globalhost+'productInfo/delete/'+row.productId).then(function (resp) {
                        if(resp.data){
                            _this.$alert('【'+row.productName+'】已删除', '', {
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
            axios.get(this.$store.state.globalhost+'productInfo/list?page=1&size='+this.pageSize).then(function (resp) {
                if(resp.data.code === -1){
                    _this.$alert(resp.data.msg, '', {
                        confirmButtonText: '确定'
                    });
                }
                _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.total
            })
            axios.get(this.$store.state.globalhost+'productCategory/categoryList').then(function (resp) {
                _this.categoryList = resp.data
            })
        }
    }
</script>
