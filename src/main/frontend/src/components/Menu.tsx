import { Link } from 'react-router-dom';

interface MenuProps {
    onLogout: () => void;
}

export default function Menu({ onLogout }: MenuProps) {
    return (
        <nav style={{ padding: '10px', borderBottom: '1px solid #ccc', display: 'flex', gap: '10px', alignItems: 'center' }}>
            <Link to="/">Dashboard</Link>
            <Link to="/profile">Profile</Link>
            <Link to="/settings">Settings</Link>
            <button type="button" onClick={onLogout} style={{ marginLeft: 'auto', padding: '5px 10px', cursor: 'pointer' }}>
                Déconnexion
            </button>
        </nav>
    );
}
