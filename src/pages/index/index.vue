<template>
  <view class="container">
    <text class="title">欢迎使用小程序</text>
    <button @click="getData" class="btn">获取数据</button>
    <text v-if="data" class="data">{{ data }}</text>
  </view>
</template>

<script setup>
import { ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'

const data = ref('')

const getData = async () => {
  try {
    const res = await uni.request({
      url: 'https://express-nq0n-152130-4-1352756383.sh.run.tcloudbase.com/api/data',
      method: 'GET'
    })
    data.value = res.data
  } catch (error) {
    uni.showToast({ title: '请求失败', icon: 'none' })
  }
}
</script>

<style lang="scss">
.container {
  padding: 40rpx;
  .title {
    font-size: 36rpx;
    color: #333;
    margin-bottom: 40rpx;
  }
  .btn {
    background: #007AFF;
    color: white;
    margin-bottom: 30rpx;
  }
  .data {
    color: #666;
    font-size: 28rpx;
  }
}
</style>