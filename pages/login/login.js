Page({
  handleLogin() {
    wx.login({
      success: res => {
        wx.request({
          url: 'https://express-nq0n-152130-4-1352756383.sh.run.tcloudbase.com/api/login',
          method: 'POST',
          data: { code: res.code },
          success: (resp) => {
            wx.showToast({ title: '登录成功' })
            wx.navigateBack()
          }
        })
      }
    })
  }
})