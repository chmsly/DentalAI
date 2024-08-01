import React from 'react';

class PatientDashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {searchTerm: '', patients: [], selectedPatient: null};

    this.handleSearchChange = this.handleSearchChange.bind(this);
    this.handleSearchSubmit = this.handleSearchSubmit.bind(this);
    this.handlePatientSelect = this.handlePatientSelect.bind(this);
  }

  handleSearchChange(event) {
    this.setState({searchTerm: event.target.value});
  }

  handleSearchSubmit(event) {
    event.preventDefault();
    searchPatients(this.state.searchTerm)
      .then(patients => this.setState({patients}))
      .catch(error => console.error(error));
  }

  handlePatientSelect(patient) {
    this.setState({selectedPatient: patient});
    // create logic to display patient details


  }
  
  render() {
    return (
      <div>
        <form onSubmit={this.handleSearchSubmit}>
          <label>
            Search Patients:
            <input type="text" value={this.state.searchTerm} onChange={this.handleSearchChange} />
          </label>
          <input type="submit" value="Search" />
        </form>
        <ul>
          {this.state.patients.map(patient => (
            <li key={patient.id} onClick={() => this.handlePatientSelect(patient)}>
              {patient.name}
            </li>
          ))}
        </ul>
        {this.state.selectedPatient && (
          <div>
            <h2>{this.state.selectedPatient.name}'s X-ray History</h2>
            {/* Display the selected patient's X-ray history here */}
          </div>
        )}
      </div>
    );
  }
}

export default PatientDashboard;