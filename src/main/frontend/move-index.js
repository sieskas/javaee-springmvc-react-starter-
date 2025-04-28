import { renameSync } from 'fs';
import { fileURLToPath } from 'url';
import { dirname, resolve } from 'path';

// Ajout nécessaire pour simuler __dirname
const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// Maintenant, tu peux utiliser __dirname normalement
const from = resolve(__dirname, '../webapp/assets/index.html');
const to = resolve(__dirname, '../webapp/WEB-INF/views/index.html');

try {
    renameSync(from, to);
    console.log('✅ index.html déplacé avec succès.');
} catch (err) {
    console.error('❌ Erreur lors du déplacement de index.html :', err);
}
