/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 10, 2025
 */
import { useEffect, useState } from "react";
import { useFoodDataMutate } from "../../hooks/useFoodDataMutate";
import { FoodData } from "../../interface/FoodData";
import "./form-modal.css";

interface InputProps {
  label: string;
  value: string | number;
  updateValue(value: any): void;
}

interface ModalProps {
  closeModal(): void;
}

const Input = ({ label, value, updateValue }: InputProps) => {
  return (
    <div className="modal-input-wrapper">
      <label>{label}</label>
      <input
        value={value}
        onChange={(event) => updateValue(event.target.value)}
      />
    </div>
  );
};

const isInvalidInput = (value: string) => {
  const forbiddenValues = ["null", "NULL", "Null"];
  return forbiddenValues.includes(value.trim());
};

export function FormModal({ closeModal }: ModalProps) {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const { mutate, isPending } = useFoodDataMutate();

  const submit = () => {
    setErrorMessage(null);

    if (
      !title ||
      isInvalidInput(title) ||
      price <= 0 ||
      !image ||
      isInvalidInput(image)
    ) {
      setErrorMessage("Todos os campos devem ser preenchidos corretamente!");
      return;
    }

    const foodData: FoodData = { title, price, image };

    mutate(foodData, {
      onError: (err: any) => {
        if (err.response) {
          if (err.response.status === 400) {
            if (err.response.data.message.includes("Mandatory fields")) {
              setErrorMessage("Preencha todos os campos corretamente.");
            } else if (
              err.response.data.message.includes("already associated")
            ) {
              setErrorMessage("A imagem já está cadastrada no sistema.");
            } else {
              setErrorMessage("Erro ao processar a requisição.");
            }
          } else {
            setErrorMessage("Erro inesperado. Tente novamente mais tarde.");
          }
        }
      },
    });
  };

  useEffect(() => {
    if (!isPending) return;
    closeModal();
  }, [isPending]);

  return (
    <div className="modal-overlay">
      <div className="modal-body">
        <h2>Inclua um novo item no cardápio:</h2>
        <form className="modal-input-container">
          <Input label="Título:" value={title} updateValue={setTitle} />
          <Input label="Preço:" value={price} updateValue={setPrice} />
          <Input label="Link da imagem:" value={image} updateValue={setImage} />
        </form>
        {errorMessage && <p className="modal-error-message">{errorMessage}</p>}
        <div className="modal-button-group">
          <button onClick={submit} className="modal-btn-insert">
            {isPending ? "Inserindo..." : "Inserir"}
          </button>
          <button onClick={closeModal} className="modal-btn-back">
            Voltar
          </button>
        </div>
      </div>
    </div>
  );
}
