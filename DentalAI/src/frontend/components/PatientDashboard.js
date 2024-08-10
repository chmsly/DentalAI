import React from 'react';

class PatientDashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchTerm: '',
      patients: [],
      selectedPatient: null,
      currentPage: 1,
      patientsPerPage: 10,
      sortField: 'name',
      isLoading: false,
      error: null
    };

    this.handleSearchChange = this.handleSearchChange.bind(this);
    this.handleSearchSubmit = this.handleSearchSubmit.bind(this);
    this.handlePatientSelect = this.handlePatientSelect.bind(this);
  }

  handleSearchSubmit(event) {
    event.preventDefault();
    this.setState({isLoading: true});
    searchPatients(this.state.searchTerm)
      .then(patients => this.setState({patients, isLoading: false}))
      .catch(error => this.setState({error, isLoading: false}));
  }

  handleSearchSubmit(event) {
    event.preventDefault();
    searchPatients(this.state.searchTerm)
      .then(patients => this.setState({patients}))
      .catch(error => console.error(error));
  }

  handlePatientSelect(patient) {
    fetchPatientDetails(patient.id)
      .then(details => this.setState({selectedPatient: {...patient, details}}))
      .catch(error => console.error(error));
  }
  
  render() {
    const { searchTerm, patients, selectedPatient, currentPage, patientsPerPage, sortField, isLoading, error } = this.state;
    const indexOfLastPatient = currentPage * patientsPerPage;
    const indexOfFirstPatient = indexOfLastPatient - patientsPerPage;
    const currentPatients = patients.slice(indexOfFirstPatient, indexOfLastPatient);

    if (isLoading) {
      return <div>Loading...</div>;
    }

    if (error) {
      return <div>Error: {error.message}</div>;
    }

    return (
      <div>
        <form onSubmit={this.handleSearchSubmit}>
          <label>
            Search Patients:
            <input type="text" value={searchTerm} onChange={this.handleSearchChange} />
          </label>
          <input type="submit" value="Search" />
        </form>
        <ul>
          {currentPatients.sort((a, b) => a[sortField] > b[sortField] ? 1 : -1).map(patient => (
            <li key={patient.id} onClick={() => this.handlePatientSelect(patient)}>
              {patient.name}
            </li>
          ))}
        </ul>
        <div>
          {Array(Math.ceil(patients.length / patientsPerPage)).fill().map((_, i) => (
            <button key={i} onClick={() => this.handlePageChange(i + 1)}>
              {i + 1}
            </button>
          ))}
        </div>
        <div>
          Sort by:
          <button onClick={() => this.handleSortChange('name')}>Name</button>
          <button onClick={() => this.handleSortChange('date')}>Date</button>
        </div>
        {selectedPatient && (
          <div>
            <h2>{selectedPatient.name}'s X-ray History</h2>
            {/* Display the selected patient's X-ray history here */}
          </div>
        )}
      </div>
    );
  }
}

export default PatientDashboard;