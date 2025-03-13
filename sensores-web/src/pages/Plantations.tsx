import PlantationTable from "../components/tables/PlantationTable";
import { PlantationProvider } from "../contexts/PlantationContext";
import Layout from "../layouts/Layout";

const Plantations = () => {
  return (
    <Layout>
      <PlantationProvider>
        <PlantationTable />
      </PlantationProvider>
    </Layout>
  );
};

export default Plantations;
