import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
 // server: {
 //      proxy: {
 //        '/**': {
 //          target: 'https://localhost:8084',
 //          changeOrigin: true,
 //          secure: false,
 //          ws: true,
 //        }
 //      }
 // }
})
