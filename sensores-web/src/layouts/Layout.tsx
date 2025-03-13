import { ReactNode } from "react";
import Header from "../components/layout/Header";
import Footer from "../components/layout/Footer";

interface LayoutProps {
  children: ReactNode;
}

export default function Layout({ children }: LayoutProps) {
  return (
    <>
      <Header />
      {children}
      <Footer />
    </>
  );
}
