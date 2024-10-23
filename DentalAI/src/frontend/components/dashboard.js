import React, { useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
  const [file, setFile] = useState(null);
  const [analysisResult, setAnalysisResult] = useState(null);

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('file', file);
    formData.append('dentistDiagnosis', 'Sample diagnosis'); // Replace with actual diagnosis input
    formData.append('userId', 'user123'); // Replace with actual user ID

    try {
      const response = await axios.post('/api/xray-analysis/analyze', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setAnalysisResult(response.data);
    } catch (error) {
      console.error('Error uploading file:', error);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input type="file" onChange={handleFileChange} />
        <button type="submit">Analyze X-ray</button>
      </form>
      {analysisResult && (
        <div>
          <h2>Analysis Result</h2>
          <pre>{JSON.stringify(analysisResult, null, 2)}</pre>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
