interface TextErrorProps {
  error: string;
}

export default function TextError({ error }: TextErrorProps) {
  return <p className="text-red-500">{error}</p>;
}
