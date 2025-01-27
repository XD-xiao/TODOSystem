const taskService = new Vue({
    el: '#TaskService',
    data: {
        taskList: [],
        categories: [],
        total: 0,
        taskid: null,
        userid: 5,
        title: null,
        text: null,
        tasktime: null,
        categoryid: null,
        createdate: null,
        page: 1,
        pageSize: 7,
        searchKey: ""
    },
    methods: {
        changePage(btn) {
            switch (btn){
                case 'prev': this.page = Math.max(this.page - 1, 1);
                    break;
                case 'next': this.page = Math.min(this.page + 1, Math.ceil(this.total / this.pageSize));
                    break;
                case 'home': this.page = 1;
                    break;
                case 'end': this.page = Math.ceil(this.total / this.pageSize);
                    break;

                default:
                    break;
            }

            console.log(this.page);
            this.getTaskSearchList(this.searchKey);

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
        fetchCategories() {
            this.categories = categoryService.categoryList; // 直接引用categoryService的数据
            console.log(this.categories);
        },
        getCategoryInfo(categoryId) {
            return this.categories.find(category => category.categoryid === categoryId) || { classname: '未分类', color: '#FFFFFF' };
        },

        getTaskSearchList(key){
            this.sendRequest('/getTaskListBySearch', 'POST', {
                taskid: null,
                userid: null,
                title: key,
                text: null,
                tasktime: null,
                categoryid: null,
                createdate: null,
                page: this.page,
                pageSize: this.pageSize

            }).then(response => {
                    if (response.code === 1) {
                        console.log(response.data);
                        this.total = response.data.total;
                        this.taskList = response.data.rows;

                    } else {
                        this.showMessage('信息获取失败:', false);
                    }
                });
        },
        validateTitle(title) {
            // 定义标题验证规则：包含英文、数字、中文，长度1~40字符
            const titleRegex = /^[\u4e00-\u9fa5A-Za-z0-9]{1,40}$/;
            return titleRegex.test(title);
        },

        validateText(text) {
            // 定义内容验证规则：包含英文、数字、中文及其他特殊字符，长度1~80字符
            const textRegex = /^[\u4e00-\u9fa5A-Za-z0-9\W_]{1,80}$/;
            return textRegex.test(text);
        },
        createTask(){
            this.fetchCategories();
            if (!this.validateTitle(this.title)) {
                this.showMessage('标题格式不正确，请重新输入', false);
                return;
            }
            if (!this.validateText(this.text)) {
                this.showMessage('内容格式不正确，请重新输入', false);
                return;
            }

            console.log('Trying to create task with:', this.title, this.text, this.tasktime, this.categoryid);

            this.sendRequest('/addTask', 'POST', {
                taskid: null,
                userid:null,
                title: this.title,
                text: this.text,
                tasktime: this.tasktime,
                categoryid: this.categoryid,
                createdate: null,
                page:null,
                pageSize: null

            }).then(response => {
                if (response.code === 1) {
                    console.log('Task created successfully:', response.data);

                    var taskPanel = document.getElementById("createTask");
                    taskPanel.style.display = "none";
                    var overlay = document.getElementById("overlay");
                    overlay.style.display = "none";

                    this.showMessage('创建成功', true);
                    this.getTaskSearchList(this.searchKey);
                }
                else {
                    this.showMessage(response.msg, false);
                }
            });
        },
        showUpdateTask(taskid){
            this.fetchCategories();
            taskService.getTask(taskid);
            this.taskid = taskid;
            var taskPanel = document.getElementById("updateTask");
            taskPanel.style.display = "block";
            var overlay = document.getElementById("overlay");
            overlay.style.display = "block";
        },
        getTask(taskid){

            this.sendRequest('/getTask', 'POST', {
                taskid: taskid,
                userid: null,
                title: null,
                text: null,
                tasktime: null,
                categoryid: null,
                createdate: null,
                page: null,
                pageSize: null

            }).then(response => {
                if (response.code === 1) {
                    console.log('Task created successfully:', response.data);
                    this.title = response.data.title;
                    this.text = response.data.text;
                    this.tasktime = response.data.tasktime;
                    this.categoryid = response.data.categoryid;

                }
                else {
                    this.showMessage(response.msg, false);
                }
            })

        },
        updateTask(){
            if (!this.validateTitle(this.title)) {
                this.showMessage('标题格式不正确，请重新输入', false);
                return;
            }
            if (!this.validateText(this.text)) {
                this.showMessage('内容格式不正确，请重新输入', false);
                return;
            }

            this.sendRequest('/updateTask', 'POST', {
                taskid: this.taskid,
                userid: null,
                title: this.title,
                text: this.text,
                tasktime: this.tasktime,
                categoryid: this.categoryid,
                createdate: null,
                page: null,
                pageSize: null

            }).then(response => {
                if (response.code === 1) {
                    console.log('Task created successfully:', response.data);
                    this.showMessage('更新成功', true);
                }
                if (response.code === 0) {
                    this.showMessage(response.msg, false);
                }
            })

        },
        deleteTask(){
            this.sendRequest('/deleteTask', 'POST', {
                taskid: this.taskid,
                userid: null,
                title: null,
                text: null,
                tasktime: null,
                categoryid: null,
                createdate: null,
                page: null,
                pageSize: null

            }).then(response => {
                if (response.code === 1) {
                    console.log('Task created successfully:', response.data);

                    var taskPanel = document.getElementById("updateTask");
                    taskPanel.style.display = "none";
                    var overlay = document.getElementById("overlay");
                    overlay.style.display = "none";

                    this.showMessage('删除成功', true);

                    taskService.getTaskSearchList(this.searchKey);
                }
                if (response.code === 0) {
                    this.showMessage(response.msg, false);
                }
            })
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

        this.getTaskSearchList("");

        // 观察categoryService的categoryList变化
        const unwatch = categoryService.$watch('categoryList', (newVal, oldVal) => {
            if (newVal.length > 0) {
                this.fetchCategories(); // 更新自己的categories属性
                unwatch(); // 停止观察，因为我们只需要在第一次加载时做这个操作
            }
        });

        // 如果categoryService已经加载了数据，直接获取
        if (categoryService.categoryList.length > 0) {
            this.fetchCategories();
        }
    }


});