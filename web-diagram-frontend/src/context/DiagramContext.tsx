import React, {createContext,useContext,useState, ReactNode} from "react";

export type ComponentType = 'bus'|'machine'|'load';

export interface ComponentDTO {
    id:string;
    type: ComponentDTO;
    title: string;
    baseKv:number|null;
    x:number;
    y:number;
}

export interface ConnectionDTO {
    id:string;
    source:string;
    target:string;
}

export interface DiagramDTO {
    components: ComponentDTO[];
    connections: ConnectionDTO[];
}

interface DiagramContextProps {
    diagram: DiagramDTO | null;
    setDiagram: (data: DiagramDTO) => void;
}

const DiagramContext = createContext<DiagramContextProps | undefined>(undefined)

export const DiagramProvider = ({children}:{children:ReactNode}) => {
    const [diagram,setDiagram] = useState<DiagramDTO|null>(null)

    return (
        <DiagramContext.Provider value={{diagram,setDiagram}}>
            {children}
        </DiagramContext.Provider>
    );
};

export const useDiagram = () => {
    const context = useContext(DiagramContext);
    if (!context) {
      throw new Error('useDiagram deve ser usado dentro de um DiagramProvider');
    }
    return context;
  };
  