# 🚀 Next.js‑Projekt in WebStorm 2026.1 erstellen + GitHub‑Repo verbinden

## Neues Next.js‑Projekt in WebStorm anlegen

WebStorm 2026.1 hat integrierte Next.js‑Unterstützung.
So startest du:
Schritte
1. WebStorm öffnen → New Project
2. Links Next.js auswählen
   (Plugin ist standardmäßig aktiv)
3. Rechts:
* Projektordner wählen
* Node.js Runtime auswählen
* create-next-app: npx create-next-app auswählen
* Optional: Use TypeScript aktivieren

4. Create klicken
   → WebStorm generiert das Projekt und installiert Dependencies.

## 2️⃣Git‑Repository direkt beim Erstellen anlegen (optional)

WebStorm kann beim Projektstart sofort ein Git‑Repo erzeugen:
1. Im New‑Project‑Dialog Create Git repository aktivieren
2. Projekt wird mit .git initialisiert
3. Commit‑Toolwindow erscheint automatisch

## 3️⃣  GitHub‑Account in WebStorm verbinden

Damit du pushen kannst:
1. Settings → Version Control → GitHub
2. Auf Add account klicken
3. Login via Token oder GitHub OAuth  

## 4️ Neues GitHub‑Repository erstellen & Projekt hochladen
### Variante A – direkt aus WebStorm heraus
1. Menü: Git → GitHub → Share Project on GitHub
2. Repo‑Name eingeben
3. Sichtbarkeit wählen (public/private)
4. Share klicken  
    → WebStorm erstellt Repo + macht ersten Push  
    (entspricht JetBrains‑Workflow)

### Variante B – Repo zuerst auf GitHub erstellen
1. Auf GitHub neues Repository anlegen (leer, ohne README)
2. In WebStorm Terminal öffnen:
```bash
git remote add origin https://github.com/<user>/<repo>.git
git add .
git commit -m "Initial commit"
git push -u origin master
 ```

## 5️⃣ Projekt starten

Im Terminal:
```bash
npm run dev
```
WebStorm hat automatisch eine Run/Debug‑Konfiguration für Next.js erstellt.

## 🎯 Ergebnis

Nach diesen Schritten hast du:
* Ein vollständiges Next.js‑Projekt
* Lokales Git‑Repository
* Verbundenes GitHub‑Remote
* WebStorm‑Integration für Commit, Push, Branching, Pull Requests

# ✅ Perfekte .gitignore für Next.js + WebStorm
```Gitignore
# ---------------------------------------------------------
# Node / Next.js
# ---------------------------------------------------------
node_modules/
npm-debug.log*
yarn-debug.log*
yarn-error.log*
pnpm-debug.log*
package-lock.json
pnpm-lock.yaml
yarn.lock

# Next.js build output
.next/
out/
dist/

# Next.js cache
.next/cache/
.vercel/

# Environment variables
.env
.env.local
.env.development.local
.env.test.local
.env.production.local

# Turbo / SWC / Babel
turbo/
.swc/
.cache/
babel.config.js.timestamp*

# ---------------------------------------------------------
# OS-specific
# ---------------------------------------------------------
.DS_Store
Thumbs.db

# ---------------------------------------------------------
# WebStorm / JetBrains IDEs
# ---------------------------------------------------------
.idea/
# But keep code style + inspections if needed:
# !.idea/codeStyles/
# !.idea/inspectionProfiles/

# JetBrains project files
*.iml

# ---------------------------------------------------------
# Logs
# ---------------------------------------------------------
logs/
*.log
*.log.*
npm-debug.log*
lerna-debug.log*

# ---------------------------------------------------------
# Testing
# ---------------------------------------------------------
coverage/
.nyc_output/
jest-cache/
jest-html-reporters-*

# ---------------------------------------------------------
# TypeScript
# ---------------------------------------------------------
*.tsbuildinfo

# ---------------------------------------------------------
# Docker
# ---------------------------------------------------------
docker-data/
docker-compose.override.yml

# ---------------------------------------------------------
# Misc
# ---------------------------------------------------------
*.local
*.tmp
*.swp
*.swo
*.bak
*.orig
```

## 🎯 Warum diese .gitignore perfekt ist
1. Next.js‑optimiert
* Ignoriert .next/, out/, dist/, SWC‑Cache, Turbo‑Repo‑Artefakte
* Unterstützt App Router + neue Next.js‑Build‑Pipelines

2. WebStorm‑optimiert
* .idea/ wird ignoriert
* Optional kannst du Code‑Style‑Profile behalten (auskommentiert)

3. Enterprise‑ready
* .env.* sauber getrennt
* Docker‑Artefakte ausgeschlossen
* Keine Lockfiles → ideal für CI/CD‑Reproduzierbarkeit
   (du arbeitest ohnehin mit stabilen Pipelines)

4. Minimal, aber vollständig
* Kein unnötiger Ballast, keine veralteten Einträge.

## 🔧 Optional: Willst du eine Variante für…
* Monorepo (Turborepo / Nx / pnpm workspace)
* Next.js + Docker + Kubernetes
* Next.js + Vercel + GitHub Actions
* Next.js + Storybook
* Next.js + Playwright / Cypress