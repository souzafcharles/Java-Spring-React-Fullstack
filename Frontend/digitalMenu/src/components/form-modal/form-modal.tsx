 /*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 03, 2025
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
      ></input>
    </div>
  );
};

export function FormModal({ closeModal }: ModalProps) {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const { mutate, isSuccess, isLoading } = useFoodDataMutate();

  const submit = () => {
    if (!title || price <= 0 || !image) {
      setErrorMessage("Todos os campos devem ser preenchidos corretamente!");
      return;
    }

    const foodData: FoodData = {
      title,
      price,
      image,
    };
    mutate(foodData);
  };

  useEffect(() => {
    if (!isSuccess) return;
    closeModal();
  }, [isSuccess]);

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
            {isLoading ? "Inserindo..." : "Inserir"}
          </button>
          <button onClick={closeModal} className="modal-btn-back">
          Voltar
        </button>
        </div>
      </div>
    </div>
  );
}