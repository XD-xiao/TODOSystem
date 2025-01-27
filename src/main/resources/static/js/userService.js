
const userService = new Vue({
	el: "#UserService",
	data: {
		id: null,
		username: null,
		email: null,
		password: null,  
		picid: null,   
		createtime: null,
		currentPanel: "login"
	},
	methods: {
		// 验证输入的方法
		validateEmail(email) {
			const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			return re.test(email);
		},
		validatePassword(password) {
		    // 密码需要3到25个字符，包含字母（大小写英文）和数字
		    const re = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{3,25}$/;
		    return re.test(password);
		},
		validateUsername(username) {
		    // 用户名可以是中文、英文（大小写）和数字，长度为3到15个字符
		    // 注意：这里的正则表达式假设一个中文字符被视为一个字符单位
		    const re = /^[\u4e00-\u9fa5a-zA-Z0-9]{3,15}$/;
		    return re.test(username);
		},
		validatePicId(picid) {
			// 假设picid是一个非空字符串
			return picid !== null && picid.trim() !== '';
		},

		// 发送请求的方法
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

		login() {
			if (!this.validateEmail(this.email)) {
				this.showMessage('请输入有效的电子邮件地址', false);
				// alert('请输入有效的电子邮件地址');
				return;
			}
			if (!this.validatePassword(this.password)) {
				this.showMessage('密码需要3到25个字符，包含字母（大小写英文）和数字', false);
				// alert('密码需要3到25个字符，包含字母（大小写英文）和数字');
				return;
			}

			console.log('Trying to log in with:', this.email, this.password);

			// 调用发送请求的方法
			this.sendRequest('/login', 'POST', {
				id:this.id,
				username: this.username,
				email: this.email,
				password: this.password,
				picid: this.picid,
				createtime: this.createtime
			}).then(response => {
				
				// console.log('Register response:', response); 
				if (response.code) {
					// console.log('Login successful');
					// 处理登录成功
					
					localStorage.setItem('Authorization', response.data);
					this.showMessage('登录成功', true);

					setTimeout(() => {
						window.location.href = '/Home.html';
					}, 1000);
				} else {
					console.error('Login failed:', response.msg);
					this.showMessage('登录失败:', false);
				}
			});
		},
		register() {
			if (!this.validateUsername(this.username)) {
				this.showMessage('用户名是中文、英文（大小写）和数字，长度为3到15个字符', false);
				// alert('用户名是中文、英文（大小写）和数字，长度为3到15个字符');
				return;
			}
			if (!this.validateEmail(this.email)) {
				this.showMessage('请输入有效的电子邮件地址', false);
				return;
			}
			if (!this.validatePassword(this.password)) {
				this.showMessage('密码需要3到25个字符，包含字母（大小写英文）和数字', false);
				// alert('密码需要3到25个字符，包含字母（大小写英文）和数字');
				return;
			}
			if (!this.validatePicId(this.picid)) {
				this.showMessage('请提供有效的头像ID', false);
				// alert('请提供有效的头像ID');
				return;
			}

			console.log('Trying to register with:', this.username, this.email, this.password, this.picid);

			// 调用发送请求的方法
			this.sendRequest('/register', 'POST', {
				id:this.id,
				username: this.username,
				email: this.email,
				password: this.password,
				picid: this.picid,
				createtime: this.createtime
			}).then(response => {
				
				// console.log('Register response:', response); 

				if (response.code) {
					// console.log('Registration successful');
					this.showMessage('注册成功', true);
				} else {
					// console.error('Registration failed:', response.msg);
					this.showMessage('注册失败:', false);
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
		},
		
		showPanel(panel){
			this.currentPanel = panel;
		},


		update(){

			var viewModes = document.getElementsByClassName('viewMode');
			for (var i = 0; i < viewModes.length; i++) {
				viewModes[i].style.display = 'none';
			}

			var updateModes = document.getElementsByClassName('updateMode');
			for (var j = 0; j < updateModes.length; j++) {
				updateModes[j].style.display = 'inline-block'; // 空字符串表示使用默认显示方式，也可以明确指定 'block' 或其他
			}

			document.querySelector('.updatebtn').style.display = 'none';
			document.querySelector('.savebtn').style.display = 'inline-block';
		},
		save(){

			if (!this.validateUsername(this.username)) {
				this.showMessage('用户名是中文、英文（大小写）和数字，长度为3到15个字符', false);
				// alert('用户名是中文、英文（大小写）和数字，长度为3到15个字符');
				return;
			}
			if (!this.validateEmail(this.email)) {
				this.showMessage('请输入有效的电子邮件地址', false);
				return;
			}
			if (!this.validatePassword(this.password)) {
				this.showMessage('密码需要3到25个字符，包含字母（大小写英文）和数字', false);
				// alert('密码需要3到25个字符，包含字母（大小写英文）和数字');
				return;
			}
			console.log('Trying to update with:', this.username, this.email, this.password);

			// 调用发送请求的方法
			this.sendRequest('/update', 'POST', {
				id:this.id,
				username: this.username,
				email: this.email,
				password: this.password,
				picid: this.picid,
				createtime: this.createtime
			}).then(response => {

				// console.log('Register response:', response);

				if (response.code) {
					// console.log('Registration successful');
					this.showMessage('修改成功', true);

				} else {
					// console.error('Registration failed:', response.msg);
					this.showMessage('修改失败:', false);
				}
			});


			var viewModes = document.getElementsByClassName('viewMode');
			for (var i = 0; i < viewModes.length; i++) {
				viewModes[i].style.display = 'inline-block';
			}

			var updateModes = document.getElementsByClassName('updateMode');
			for (var j = 0; j < updateModes.length; j++) {
				updateModes[j].style.display = 'none'; // 空字符串表示使用默认显示方式，也可以明确指定 'block' 或其他
			}

			document.querySelector('.savebtn').style.display = 'none';
			document.querySelector('.updatebtn').style.display = 'inline-block';
		},
		getUserInfo: function () {
			console.log("获取用户信息");
			// 调用发送请求的方法
			this.sendRequest('/getUserById', 'POST', {
			}).then(response => {
				if (response.code===1) {
					// console.log('Registration successful');
					console.log(response.data);
					// this.showMessage('获取成功', true);

					this.username = response.data.name;
					this.email = response.data.email;
					this.password = response.data.password;
					this.picid = response.data.picid;
					this.createtime = response.data.createdate;
					this.id = response.data.id;

				} else {
					// console.error('Registration failed:', response.msg);
					this.showMessage('信息获取失败:', false);
				}
			});
		}


	},
	mounted() {
		// 当Vue实例挂载到DOM上后，立即调用 getUserInfo 方法
		this.getUserInfo();
	}
});