// Check if is production mode with vite
const isProduction = import.meta.env.PROD;

export const CONSTS = {
  API_URL: isProduction ? "/api" : "https://sensores.comparitiko.dev/api",
};
