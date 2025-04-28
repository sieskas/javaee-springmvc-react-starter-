import { renameSync, existsSync, unlinkSync, mkdirSync, readdirSync, copyFileSync } from 'fs';
import { fileURLToPath } from 'url';
import { dirname, resolve } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// Dossiers sources
const buildDir = resolve(__dirname, './dist'); // Vite build ici
const assetsDir = resolve(buildDir, 'assets');
const indexHtml = resolve(buildDir, 'index.html');

// Dossiers cibles
const targetAssetsDir = resolve(__dirname, '../webapp/assets'); // <-- bien assets
const targetIndexHtmlDir = resolve(__dirname, '../webapp/WEB-INF/views');
const targetIndexHtml = resolve(targetIndexHtmlDir, 'index.html');

// Déplacer assets
function moveAssets() {
    mkdirSync(targetAssetsDir, { recursive: true }); // Créer /assets/ s'il n'existe pas
    const files = readdirSync(assetsDir);
    files.forEach(file => {
        const src = resolve(assetsDir, file);
        const dest = resolve(targetAssetsDir, file);
        copyFileSync(src, dest);
    });
    console.log('✅ Assets copiés avec succès.');
}

// Déplacer index.html
function moveIndex() {
    mkdirSync(targetIndexHtmlDir, { recursive: true }); // Créer /WEB-INF/views/ s'il n'existe pas
    renameSync(indexHtml, targetIndexHtml);
    console.log('✅ index.html déplacé avec succès.');
}

try {
    moveAssets();
    moveIndex();
} catch (err) {
    console.error('❌ Erreur lors du déplacement des fichiers :', err);
}
