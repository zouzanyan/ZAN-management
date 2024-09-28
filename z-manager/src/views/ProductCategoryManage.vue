<template>
    <div style="margin-top: 30px; margin-left: 30px; border: 0px solid red">
        <el-form label-width="100px" class="demo-ruleForm">
            <el-button
                    @click="dialogFormVisible = true"
                    type="primary"
                    icon="el-icon-plus"
                    style="float: left; margin-bottom: 20px"
            >添加商品分类
            </el-button
            >
        </el-form>

        <!-- 添加供应商对话框 -->
        <el-dialog title="添加商品分类" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="分类编号">
                    <el-input v-model="ruleForm.categoryId"></el-input>
                </el-form-item>
                <el-form-item label="分类名称">
                    <el-input v-model="ruleForm.categoryName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="编辑分类" :visible.sync="dialogFormVisibleEdit">
            <el-form
                    :model="ruleFormEdit"
                    ref="ruleFormEdit"
                    label-width="100px"
                    class="demo-ruleForm"
            >
                <el-form-item label="分类编号">
                    <el-input v-model="ruleFormEdit.categoryId" readOnly></el-input>
                </el-form-item>
                <el-form-item label="分类名称">
                    <el-input v-model="ruleFormEdit.categoryName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitFormEdit('ruleFormEdit')"
                    >立即修改
                    </el-button
                    >
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-table :data="tableData" border stripe style="width: 100%">
            <el-table-column prop="categoryId" label="分类编号" width="200"></el-table-column>
            <el-table-column prop="categoryName" label="分类名称" width=""></el-table-column>

            <!-- fixed属性右固定 -->
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="mini" @click="edit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="del(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                style="margin-top: 20px; float: right"
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                :current-page.sync="currentPage"
                @current-change="page"
        >
        </el-pagination>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {
                dialogFormVisible: false,
                dialogFormVisibleEdit: false,
                ruleForm: {
                    // categoryId: 'c001',
                    // categoryName: '百胜',
                },
                ruleFormEdit: "",
                // 表格数据
                tableData: null,
                // 页面大小
                pageSize: 5,
                // 总数据条数
                total: null,
                // 当前页码
                currentPage: 1,
            };
        },
        methods: {
            submitForm() {
                const _this = this;
                axios
                    .post(this.$store.state.globalhost + "productCategory/save", _this.ruleForm)
                    .then(function (resp) {
                        if (resp.data) {
                            _this.$alert(_this.ruleForm.categoryName + "添加成功", "", {
                                confirmButtonText: "确定",
                                callback: (action) => {
                                    location.reload();
                                },
                            });
                        }
                    });
            },
            submitFormEdit() {
                const _this = this;
                axios
                    .put(this.$store.state.globalhost + "productCategory/update", _this.ruleFormEdit)
                    .then(function (resp) {
                        if (resp.data) {
                            _this.$alert(_this.ruleFormEdit.categoryName + "修改成功", "", {
                                confirmButtonText: "确定",
                                callback: (action) => {
                                    location.reload();
                                },
                            });
                        }
                    });
            },
            page(currentPage) {
                const _this = this;
                axios
                    .get(
                        this.$store.state.globalhost +
                        "productCategory/list?page=" +
                        currentPage +
                        "&size=" +
                        _this.pageSize
                    )
                    .then(function (resp) {
                        _this.tableData = resp.data.content;
                        _this.pageSize = resp.data.size;
                        _this.total = resp.data.total;
                    });
            },
            edit(row) {
                this.ruleFormEdit = {...row};
                this.dialogFormVisibleEdit = true;
            },
            del(row) {
                const _this = this;
                this.$confirm("确认删除【" + row.categoryName + "】吗？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        axios
                            .delete(
                                this.$store.state.globalhost + "productCategory/delete/" + row.categoryId
                            )
                            .then(function (resp) {
                                if (resp.data) {
                                    _this.$alert("【" + row.categoryName + "】已删除", "", {
                                        confirmButtonText: "确定",
                                        callback: (action) => {
                                            location.reload();
                                        },
                                    });
                                }
                            });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },
        },
        created() {
            const _this = this;
            axios
                .get(
                    this.$store.state.globalhost + "productCategory/list?page=1&size=" + this.pageSize
                )
                .then(function (resp) {
                    if (resp.data.code == -1) {
                        _this.$alert(resp.data.msg, "", {
                            confirmButtonText: "确定",
                        });
                    }
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.total;

                });
        },
    };
</script>
