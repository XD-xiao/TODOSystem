const categoryService = new Vue({
    el: "#CategoryService",
    data: {
        categoryList: [],
        newCategory: {
            categoryid: null,
            userid: null, // 假设userid是固定的或者从其他地方获取
            classname: '',
            color: '#FFFFFF' // 默认颜色值，去掉空格
        },
        showNewCategoryRow: false
    },
    methods: {
        validateName(name) {
            const re = /^[\u4e00-\u9fa5a-zA-Z0-9]{1,15}$/;
            return re.test(name);
        },
        sendRequest(url, method, data) {
            return fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('Authorization'),
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .catch(error => console.error('Error:', error));
        },
        getCategoryList() {
            console.log("获取分类列表");
            this.sendRequest('/getCategoryListByUserId', 'POST', {})
                .then(response => {
                    if (response.code === 1) {
                        console.log(response.data);
                        this.categoryList = response.data.map(item => ({
                            ...item,
                            color: item.color.replace(/\s/g, '') // Remove any spaces from the color code
                        }));
                    } else {
                        this.showMessage('信息获取失败:', false);
                    }
                });
        },

        addCategory() {
            this.showNewCategoryRow = true;
            // 可选：清空新分类的信息
            this.newCategory.classname = '';
            this.newCategory.color = '#FF5733';
        },

        cancelAddCategory() {
            this.showNewCategoryRow = false;
        },

        saveCategory() {
            if (!this.validateName(this.newCategory.classname)) {
                this.showMessage('类别名称不符合规范', false);
                return;
            }

            const newCategory = {
                categoryid: this.newCategory.categoryid,
                userid: this.newCategory.userid,
                classname: this.newCategory.classname,
                color: this.newCategory.color.replace(/\s/g, '') // Remove any spaces from the color code
            };

            this.sendRequest('/addCategory', 'POST', newCategory)
                .then(response => {
                    if (response.code === 1) {
                        console.log('Category created successfully:', response.data);
                        this.showMessage('创建成功', true);

                        // Add the new category to the local list immediately to reflect changes
                        this.categoryList.push({
                            ...newCategory,
                            categoryid: response.data.categoryid // Assuming server returns the new category ID
                        });

                        // Hide the new category row after successful creation
                        this.cancelAddCategory();
                        this.getCategoryList();
                    } else {
                        this.showMessage('创建失败: ' + response.msg, false);
                    }
                });
        },


        updateCategory(item) {
            if (!this.validateName(item.classname)) {
                this.showMessage('类别名称不符合规范', false);
                return;
            }

            const updatedItem = {
                categoryid: item.categoryid,
                userid: item.userid,
                classname: item.classname,
                color: item.color.replace(/\s/g, '') // Remove any spaces from the color code
            };

            this.sendRequest('/updateCategory', 'POST', updatedItem)
                .then(response => {
                    if (response.code === 1) {
                        console.log('Category updated successfully:', response.data);
                        this.showMessage('修改成功', true);
                        // Optionally, you can refresh the category list to reflect changes
                        this.getCategoryList();
                    } else {
                        this.showMessage('修改失败: ' + response.msg, false);
                    }
                });
        },

        deleteCategory(item) {
            const deletedItem = {
                categoryid: item.categoryid,
                userid: item.userid
            };

            this.sendRequest('/deleteCategory', 'POST', deletedItem)
                .then(response => {
                    if (response.code === 1) {
                        console.log('Category deleted successfully:', response.data);
                        this.showMessage('删除成功', true);

                        // Remove the category from the local list immediately to reflect changes
                        this.categoryList = this.categoryList.filter(category => category.categoryid !== item.categoryid);
                        this.getCategoryList();
                    } else {
                        this.showMessage('删除失败: ' + response.msg, false);
                    }
                });

        },
        showMessage(message, success) {
            const messageDiv = document.getElementById('message');
            messageDiv.textContent = message;
            messageDiv.style.display = 'block'; // 显示消息框

            // 设置3秒后的操作
            setTimeout(() => {
                messageDiv.style.display = 'none'; // 隐藏消息框
            }, 3000);

            // 如果你想改变样式（例如成功或失败的颜色），可以根据 success 参数调整样式
            if (success !== undefined) {
                if (success) {
                    messageDiv.className = 'success';
                } else {
                    messageDiv.className = 'error';
                }
            }
        }
    },
    mounted() {
        this.getCategoryList();
    }
});