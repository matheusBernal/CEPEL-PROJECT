import { AppBar, Box, Button, Container, Toolbar } from '@mui/material'
import React,{ useRef } from 'react'
import axios from 'axios';
import { useDiagram } from '../context/DiagramContext';


export default function Header() {

  const { setDiagram } = useDiagram();

  const fileUploadRef = useRef<HTMLInputElement>(null);

  const handleButtonClick = () => {
    fileUploadRef.current?.click()
  }

  const handleFileChange = async  (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const formData = new FormData();
      formData.append("file",file);
      const res = await axios.post("http://localhost:8080/diagram/upload", formData);
      setDiagram(res.data)
      console.log(res.data)
    }
  }

  //const [importXLM,]
  return (
    <AppBar position='static'>
      <Container maxWidth='xl' style={{
        backgroundColor: 'white',
        paddingBottom: '8px',
        borderBottom: '2px solid transparent',
        borderImage: 'linear-gradient(to right,#0072C6 0%, #009E49 50%, #C9DA2A 100%) 1',
        minHeight: '80px'
      }}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Box
            component="img"
            src="EletrobrasLogo.png"
            alt="Logo"
            sx={{
              height: 70,
              width: 110,
              p: 1,
              objectFit: 'contain',
            }} />
          <Box sx={{ display: 'flex', alignItems: 'right' }}>
            <Button
              variant="outlined"
              style={{ color: 'linear-gradient(to right,#0072C6 0%, #009E49 50%, #C9DA2A 100%) 1' }}
              sx={{
                margin: '3px',
                fontWeight: 600,
                textTransform: 'none',
                borderRadius: 2,
                px: 3,
                py: 1
              }}
              disabled
              hidden
            >
              Download
            </Button>
            <Button
              variant="outlined"
              style={{ color: 'linear-gradient(to right,#0072C6 0%, #009E49 50%, #C9DA2A 100%) 1' }}
              sx={{
                margin: '3px',
                fontWeight: 600,
                textTransform: 'none',
                borderRadius: 2,
                px: 3,
                py: 1
              }}
              onClick={handleButtonClick}
            >
              <input type='file' ref={fileUploadRef} onChange={handleFileChange} hidden/>
              Importar
            </Button>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  )
}
