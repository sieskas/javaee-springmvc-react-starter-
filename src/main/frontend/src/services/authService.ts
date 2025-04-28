import api from './api.ts'

export async function logout(): Promise<void> {
    await api.post('/api/v1/auth/logout');
}