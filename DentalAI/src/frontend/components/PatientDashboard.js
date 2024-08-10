import React from 'react';
import ImageGallery from 'react-image-gallery';

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

  handleSearchChange(event) {
    this.setState({searchTerm: event.target.value});
  }

  handleSearchSubmit(event) {
    event.preventDefault();
    this.setState({isLoading: true});
    searchPatients(this.state.searchTerm)
      .then(patients => this.setState({patients, isLoading: false}))
      .catch(error => this.setState({error, isLoading: false}));
  }

  handlePatientSelect(patient) {
    this.setState({selectedPatient: patient});
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

    const images = selectedPatient ? selectedPatient.xrayImages.map(imageUrl => ({
      original: imageUrl,
      thumbnail: imageUrl,
    })) : [];

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
        {selectedPatient && (
          <div>
            <h2>{selectedPatient.name}'s X-ray History</h2>
            <ImageGallery items={images} />
          </div>
        )}
      </div>
    );
  }
}

export default PatientDashboard;