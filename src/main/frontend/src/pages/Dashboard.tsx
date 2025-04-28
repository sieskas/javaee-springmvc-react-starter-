export default function Dashboard() {
    return (
        <div style={{ padding: '20px' }}>
            <h1 style={{ fontSize: '2rem', marginBottom: '20px' }}>Dashboard</h1>
            <div style={{ display: 'flex', gap: '20px', flexWrap: 'wrap' }}>
                <div style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '20px', flex: '1' }}>
                    <h2>Card 1</h2>
                    <p>Content for the first card.</p>
                </div>
                <div style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '20px', flex: '1' }}>
                    <h2>Card 2</h2>
                    <p>Content for the second card.</p>
                </div>
                <div style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '20px', flex: '1' }}>
                    <h2>Card 3</h2>
                    <p>Content for the third card.</p>
                </div>
            </div>
        </div>
    )
}