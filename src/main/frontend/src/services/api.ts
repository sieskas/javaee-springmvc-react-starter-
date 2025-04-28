import axios from 'axios';
import { APP_BASE_PATH } from '../config/constants';

const api = axios.create({
    baseURL: APP_BASE_PATH,
    withCredentials: true,
});

export default api;
