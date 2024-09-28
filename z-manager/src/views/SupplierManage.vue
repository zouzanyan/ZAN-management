<template>
  <div style="margin-top: 30px; margin-left: 30px; border: 0px solid red">
    <el-form label-width="100px" class="demo-ruleForm">
      <el-button
        @click="dialogFormVisible = true"
        type="primary"
        icon="el-icon-plus"
        style="float: left; margin-bottom: 20px"
        >添加供应商</el-button
      >
    </el-form>

    <!-- 添加供应商对话框 -->
    <el-dialog title="添加供应商" :visible.sync="dialogFormVisible">
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="供应商编码">
          <el-input v-model="ruleForm.supplierCode"></el-input>
        </el-form-item>
        <el-form-item label="供应商名称">
          <el-input v-model="ruleForm.supplierName"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model.number="ruleForm.contact"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="ruleForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="发票号">
          <el-input v-model="ruleForm.taxId"></el-input>
        </el-form-item>
        <el-form-item label="开户行">
          <el-input v-model="ruleForm.bankName"></el-input>
        </el-form-item>
        <el-form-item label="银行账户">
          <el-input v-model="ruleForm.bankAccount"></el-input>
        </el-form-item>
        <el-form-item label="户名">
          <el-input v-model="ruleForm.bankChairman"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="ruleForm.remark"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="编辑供应商" :visible.sync="dialogFormVisibleEdit">
      <el-form
        :model="ruleFormEdit"
        ref="ruleFormEdit"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="供应商编号">
          <el-input v-model="ruleFormEdit.supplierId" readOnly></el-input>
        </el-form-item>
        <el-form-item label="供应商编码">
          <el-input v-model="ruleFormEdit.supplierCode"></el-input>
        </el-form-item>
        <el-form-item label="供应商名称">
          <el-input v-model="ruleFormEdit.supplierName"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="ruleFormEdit.address"></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model.number="ruleFormEdit.contact"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱">
          <el-input v-model="ruleFormEdit.email"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="ruleFormEdit.phone"></el-input>
        </el-form-item>
        <el-form-item label="发票号">
          <el-input v-model="ruleFormEdit.taxId"></el-input>
        </el-form-item>
        <el-form-item label="开户行">
          <el-input v-model="ruleFormEdit.bankName"></el-input>
        </el-form-item>
        <el-form-item label="银行账户">
          <el-input v-model="ruleFormEdit.bankAccount"></el-input>
        </el-form-item>
        <el-form-item label="户名">
          <el-input v-model="ruleFormEdit.bankChairman"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="ruleFormEdit.remark"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitFormEdit('ruleFormEdit')"
            >立即修改</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column fixed prop="supplierId" label="供应商编号" width="100">
      </el-table-column>
      <el-table-column prop="supplierCode" label="供应商编码" width="100">
      </el-table-column>
      <el-table-column prop="supplierName" label="供应商名称" width="100">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="100"> </el-table-column>
      <el-table-column prop="contact" label="联系人" width="100"> </el-table-column>
      <el-table-column prop="email" label="电子邮箱" width="120"> </el-table-column>
      <el-table-column prop="phone" label="联系电话" width="130"> </el-table-column>
      <el-table-column prop="taxId" label="发票号" width="80"> </el-table-column>
      <el-table-column prop="bankName" label="开户行" width="80"> </el-table-column>
      <el-table-column prop="bankAccount" label="银行账户" width="120"> </el-table-column>
      <el-table-column prop="bankChairman" label="户名" width="80"> </el-table-column>
      <el-table-column prop="remark" label="备注" width="80"> </el-table-column>
      <!-- fixed属性右固定 -->
      <el-table-column fixed="right" label="操作" width="150">
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

export default {
  data() {
    return {
      dialogFormVisible: false,
      dialogFormVisibleEdit: false,
      ruleForm: {

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
        .post(this.$store.state.globalhost + "supplier/save", _this.ruleForm)
        .then(function (resp) {
          if (resp.data) {
            _this.$alert(_this.ruleForm.supplierName + "添加成功", "", {
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
        .put(this.$store.state.globalhost + "supplier/update", _this.ruleFormEdit)
        .then(function (resp) {
          if (resp.data) {
            _this.$alert(_this.ruleFormEdit.supplierName + "修改成功", "", {
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
            "supplier/list?page=" +
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
      // 这里如果不用值传递的话，不修改保存也会更新table页面
      this.ruleFormEdit = Object.assign({}, row);
      this.dialogFormVisibleEdit = true;
    },

    del(row) {
      const _this = this;
      this.$confirm("确认删除【" + row.supplierName + "】吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios
            .delete(this.$store.state.globalhost + "supplier/delete/" + row.supplierId)
            .then(function (resp) {
              if (resp.data) {
                _this.$alert("【" + row.supplierName + "】已删除", "", {
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
      .get(this.$store.state.globalhost + "supplier/list?page=1&size=" + this.pageSize)
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
