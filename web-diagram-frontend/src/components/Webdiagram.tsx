import React from 'react';
import ReactFlow, { Background, Controls, Node, Edge } from 'reactflow';
import 'reactflow/dist/style.css';
import { useDiagram } from '../context/DiagramContext';

export default function Webdiagram() {
  const { diagram } = useDiagram();

  if (!diagram) {
    return <p style={{ padding: 24 }}>Nenhum diagrama carregado.</p>;
  }

  // Convertendo components para nodes
  const nodes: Node[] = diagram.components.map(component => {
    const isBus = String(component.type) === 'bus';
  
    return {
      id: component.id,
      position: { x: component.x, y: component.y },
      data: { label: component.title },
      type: 'default',
      style: {
        width: isBus ? 20 : 50,
        height: isBus ? 150 : 50,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        fontWeight: 600,
        color: 'white',
        borderRadius: 8,
        border: '1px solid #000',
        background: isBus ? getBusColor(component.baseKv) : '#666' // cinza para machine/load
      }
    };
  });

  // Convertendo connections para edges
  const edges: Edge[] = diagram.connections.map(connection => ({
    id: connection.id,
    source: connection.source,
    target: connection.target,
    type: 'default'
  }));

  return (
    <div style={{ width: '100%', height: 'calc(100vh - 100px)' }}>
      <ReactFlow nodes={nodes} edges={edges} fitView>
        <Background />
        <Controls />
      </ReactFlow>
    </div>
  );
}

// Função auxiliar pra cor dos bus por baseKv
function getBusColor(kv: number | null) {
  if (kv === null) return '#ddd';
  if (kv >= 400) return '#D32F2F';   // vermelho
  if (kv >= 230) return '#1976D2';   // azul
  if (kv >= 138) return '#388E3C';   // verde
  return '#FBC02D';                  // amarelo
}
