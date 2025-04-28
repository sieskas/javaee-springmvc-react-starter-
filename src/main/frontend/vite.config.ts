import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { APP_BASE_PATH } from './src/config/constants';

export default defineConfig({
  plugins: [react()],
  base: `${APP_BASE_PATH}/`,
  build: {
    emptyOutDir: true,
  },
})
