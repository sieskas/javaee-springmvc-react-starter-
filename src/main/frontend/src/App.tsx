import {BrowserRouter, Link, Navigate, Route, Routes} from 'react-router-dom';
import Dashboard from "./pages/Dashboard.tsx";
import {useState} from "react";

// Menu simple pour naviguer
function Menu({ onLogout }: { onLogout: () => void }) {
    return (
        <nav style={{ padding: '10px', borderBottom: '1px solid #ccc', display: 'flex', gap: '10px', alignItems: 'center' }}>
            <Link to="/">Dashboard</Link>
            <Link to="/profile">Profile</Link>
            <Link to="/settings">Settings</Link>
            <button onClick={onLogout} style={{ marginLeft: 'auto', padding: '5px 10px', cursor: 'pointer' }}>
                Déconnexion
            </button>
        </nav>
    );
}

// Pages fictives
function Profile() {
    return <h2 style={{ padding: '20px' }}>Profile Page</h2>;
}

function Settings() {
    return <h2 style={{ padding: '20px' }}>Settings Page</h2>;
}

// Composant principal
function App() {
    const [isLoggedOut] = useState(false);

    async function handleLogout() {
        try {
            const contextPath = window.location.pathname.split('/')[1];
            const response = await fetch(`/${contextPath}/api/v1/login/logout`, {
                method: 'POST',
                credentials: 'include',
            });

            if (response.ok) {
                console.log('Déconnexion réussie');
            } else {
                console.error('Erreur pendant la déconnexion', response.status);
            }
        } catch (error) {
            console.error('Erreur réseau pendant la déconnexion:', error);
        } finally {
            window.location.href = `/${window.location.pathname.split('/')[1]}/login.jsp`;
        }
    }



    return (
        <>
            {!isLoggedOut && <Menu onLogout={handleLogout} />}
            <Routes>
                <Route path="/" element={<Dashboard />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
        </>
    );
}

export default function AppWrapper() {
    return (
        <BrowserRouter basename="/app">
            <App />
        </BrowserRouter>
    );
}

