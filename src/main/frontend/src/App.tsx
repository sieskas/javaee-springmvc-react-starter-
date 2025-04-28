import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Profile from './pages/Profile';
import Settings from './pages/Settings';
import Menu from './components/Menu';
import { useLogout } from './hooks/useLogout';
import { APP_BASE_PATH } from './config/constants';

export default function App() {
    const { mutate: handleLogout } = useLogout();

    return (
        <BrowserRouter basename={APP_BASE_PATH}>
            <Menu onLogout={handleLogout} />
            <Routes>
                <Route path="/" element={<Dashboard />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
        </BrowserRouter>
    );
}
