import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 8000
    //   proxy: {
    //     '/v1': {
    //       target: 'https://localhost:8084',
    //       changeOrigin: true,
    //       secure: false,
    //       ws: true,
    //     }
    //   }
  }
})
