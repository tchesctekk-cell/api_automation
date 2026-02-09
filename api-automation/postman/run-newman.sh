
#!/bin/bash

# Script para executar collection Postman via Newman
# Gera relatorios CLI, JUnit e HTML

echo "==========================================="
echo "Executando Testes Postman via Newman"
echo "==========================================="

newman run JSONPlaceholder_API.postman_collection.json \
  -e environment.postman_environment.json \
  --reporters cli,junit,htmlextra \
  --reporter-junit-export ../reports/newman-report.xml \
  --reporter-htmlextra-export ../reports/newman-report.html \
  --reporter-htmlextra-title "JSONPlaceholder API - Relatorio de Testes" \
  --reporter-htmlextra-darkTheme

echo ""
echo "Relatorios gerados:"
echo "- JUnit: reports/newman-report.xml"
echo "- HTML: reports/newman-report.html"