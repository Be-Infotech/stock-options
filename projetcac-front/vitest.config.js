import { fileURLToPath } from 'node:url'
import { mergeConfig, defineConfig } from 'vite'
import { configDefaults } from 'vitest/config'
import viteConfig from './vite.config'

export default mergeConfig(
  viteConfig,
  defineConfig({
    test: {
      environment: 'happy-dom',
      root: fileURLToPath(new URL('./', import.meta.url)),
      transformMode: {
        web: [/\.[jt]sx$/]
      },
      setupFiles: "./src/vitest.setup.ts",
      coverage: {
        provider: 'v8',
        reporter: ['html', 'lcov']
      },
      sequence: {
        shuffle: true,
      },
    }
  })
)
