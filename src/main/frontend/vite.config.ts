import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  base: '/app/', // Base absolue pour Tomcat dans /app/
  build: {
    emptyOutDir: true, // nettoyer dist avant build
  }
})
