# XrayAI - Dental X-Ray Analysis System

XrayAI is a modern web application that combines artificial intelligence with dental expertise to analyze dental X-rays. The system helps dentists validate their diagnoses and potentially identify overlooked anomalies in dental X-ray images.

## 🚀 Features

- **AI-Powered Analysis**: Automated detection of common dental anomalies
- **Diagnosis Comparison**: Compare AI findings with dentist's diagnosis
- **Agreement Scoring**: Quantitative measure of AI and human diagnostic agreement
- **Smart Recommendations**: Contextual recommendations based on findings
- **Modern UI**: Clean, responsive interface built with React and Material-UI
- **Real-time Processing**: Instant analysis and results display

## 🛠️ Technology Stack

### Frontend
- React 18 with Vite
- Material-UI (MUI)
- Axios for API communication
- Modern ES6+ JavaScript

### Backend
- Java 17
- Spring Boot 3.1.5
- RESTful API architecture
- Multipart file handling
- CORS security configuration

## 🏗️ Architecture

The application follows a modern client-server architecture:

1. **Frontend Layer**: React-based SPA handling user interactions and file uploads
2. **API Layer**: RESTful endpoints managing data flow and business logic
3. **Service Layer**: Core business logic and AI model integration
4. **Analysis Layer**: AI model processing and result generation

## 🚦 Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- Maven 3.6 or higher
- Git

### Clone the Repository
\`\`\`bash
git clone https://github.com/yourusername/xrayai.git
cd xrayai
\`\`\`

### Backend Setup
1. Navigate to the backend directory:
\`\`\`bash
cd backend
\`\`\`

2. Build the project:
\`\`\`bash
./mvnw clean install
\`\`\`

3. Run the Spring Boot application:
\`\`\`bash
./mvnw spring-boot:run
\`\`\`

The backend server will start on http://localhost:8080

### Frontend Setup
1. Open a new terminal and navigate to the frontend directory:
\`\`\`bash
cd xrayai-frontend
\`\`\`

2. Install dependencies:
\`\`\`bash
npm install
\`\`\`

3. Start the development server:
\`\`\`bash
npm run dev
\`\`\`

The frontend will be available at http://localhost:5173

### Verify Installation
1. Open your browser and navigate to http://localhost:5173
2. You should see the XrayAI interface
3. Try uploading an X-ray image to test the system

### Troubleshooting
- If port 8080 is in use, modify the port in \`application.properties\`:
  \`\`\`properties
  server.port=8081
  \`\`\`
- If you see CORS errors, verify the WebConfig.java has the correct frontend URL
- For file upload issues, check the multipart settings in application.properties

### Development
- Backend API endpoint: http://localhost:8080/api/xrays/analyze
- Frontend dev server: http://localhost:5173
- Hot reload is enabled for both frontend and backend

## 💡 Usage

1. Upload a dental X-ray image
2. Enter your diagnosis
3. Submit for analysis
4. Review the AI findings and comparison results
5. Check the agreement score and recommendations

## 🔒 Security

- File upload validation
- CORS configuration
- Content-type verification
- Error handling and validation

## 🛣️ Roadmap

- [ ] AI Model Enhancement
- [ ] User Authentication
- [ ] Case History Tracking
- [ ] Advanced Analytics Dashboard
- [ ] Mobile App Integration

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- Chris Hemsley - Initial work and maintenance

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- React team for the frontend framework
- Material-UI team for the component library
